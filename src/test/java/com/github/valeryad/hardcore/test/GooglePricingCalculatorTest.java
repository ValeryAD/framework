package com.github.valeryad.hardcore.test;

import com.github.valeryad.hardcore.page.GoogleCloudEstimateResultsPage;
import com.github.valeryad.hardcore.page.GoogleCloudHomePage;
import com.github.valeryad.hardcore.page.TenMinutesEmailPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GooglePricingCalculatorTest {
    private final static String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";
    private final static int NUMBER_OF_INSTANCES = 4;
    private final static String OPERATING_SYSTEM_SOFTWARE = "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS";
    private final static String VM_CLASS = "Regular";
    private static final String SERIES = "N1";
    private static final String INSTANCE_TYPE = "n1-standard-8";
    private static final int GPUS_NUMBER = 1;
    private static final String GPU_TYPE = "NVIDIA Tesla T4";
    private static final String LOCAL_SSD = "2x375";
    private static final String REGION = "Frankfurt";
    private static final String COMMITTED_USAGE = "1 Year";
    private static final String REGEX = "(\\d+,)*\\d+(\\.\\d{1,2})?";
    private static final double DELTA = 0.01;


    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    private void manageDriverBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "")
    public void estimationCostFromGoogleCalculatorShouldBeEqualToEstimationCostReceivedByEmail() {
        GoogleCloudEstimateResultsPage resultsPage = performEstimateByGoogleCloudCalculator();

        String estimatedCostFromCalculator = resultsPage.readEstimatedCost();
        String estimatedCostReceivedByEmail = receiveEstimationCostByEmail(resultsPage);

        Assert.assertEquals(getPriceNumberFromEstimatedCostString(estimatedCostFromCalculator),
                getPriceNumberFromEstimatedCostString(estimatedCostReceivedByEmail), DELTA);

    }

    private GoogleCloudEstimateResultsPage performEstimateByGoogleCloudCalculator() {
        return new GoogleCloudHomePage(driver)
                .openPage()
                .searchResultsByTerm(SEARCH_TERM)
                .selectPricingCalculatorResult(SEARCH_TERM)
                .fillNumberOfInstancesField(NUMBER_OF_INSTANCES)
                .selectOperatingSystemAndSoftware(OPERATING_SYSTEM_SOFTWARE)
                .selectVMClass(VM_CLASS)
                .selectSeriesAndInstanceType(SERIES, INSTANCE_TYPE)
                .addGPUs(GPUS_NUMBER, GPU_TYPE)
                .addLocalSSD(LOCAL_SSD)
                .selectDataCenterLocation(REGION)
                .selectCommittedUsageTerm(COMMITTED_USAGE)
                .estimate();
    }


    public String receiveEstimationCostByEmail(GoogleCloudEstimateResultsPage resultsPage) {

        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        String googleCloudHandle = String.valueOf(tabList.get(0));
        String tenMinMailHandle = String.valueOf(tabList.get(1));
        driver.switchTo().window(tenMinMailHandle);

        TenMinutesEmailPage tenMinMailPage = new TenMinutesEmailPage(driver);
        tenMinMailPage.openPage();
        String email = tenMinMailPage.readEmail();

        driver.switchTo().window(googleCloudHandle);

        resultsPage.emailEstimate()
                .sendEmail(email);

        driver.switchTo().window(tenMinMailHandle);

        return tenMinMailPage.readEstimatedPrice();
    }

    private Double getPriceNumberFromEstimatedCostString(String estimatedCost) {
        System.out.println(estimatedCost);
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(estimatedCost);
        matcher.find();
        return Double.parseDouble(matcher.group().replace(",", ""));
    }

    @AfterClass(alwaysRun = true)
    private void shutDownDriver() {
        driver.quit();
        driver = null;
    }
}
