package com.github.valeryad.hurtmeplanty.test;

import com.github.valeryad.hurtmeplanty.page.GoogleCloudEstimationResultsPage;
import com.github.valeryad.hurtmeplanty.page.GoogleCloudHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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


    private WebDriver driver;
    private GoogleCloudEstimationResultsPage estimationResultsPage;

    @BeforeClass(alwaysRun = true)
    private void manageBrowserAndPerformEstimate() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        estimationResultsPage = performEstimate();
    }

    private GoogleCloudEstimationResultsPage performEstimate() {
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

        @Test(description = "\"VM class\" section in estimation results should contain class of virtual machine that has been pointed out")
    public void vmClassInEstimationResultsShouldContainSelectedVMClass() {
        Assert.assertTrue(estimationResultsPage.readResultsVMClass().contains(VM_CLASS.toLowerCase()));
    }

    @Test(description = "\"Instance type\" section in estimation results should contain instance type that have been pointed out")
    public void instanceTypeInEstimationResultsShouldContainSelectedInstance() {
        Assert.assertTrue(estimationResultsPage.readResultsInstanceType().contains(INSTANCE_TYPE));
    }

    @Test(description = "\"Region\" section in estimation results should contain region that have been pointed out")
    public void regionInEstimationResultsShouldContainSelectedRegion() {
        Assert.assertTrue(estimationResultsPage.readResultsRegion().contains(REGION));
    }

    @Test(description = "\"Total available local SSD space\" section in estimation results should contain local SSD option that have been pointed out")
    public void localSSDInEstimationResultsShouldContainSelectedSSDOption() {
        Assert.assertTrue(estimationResultsPage.readResultsLocalSSDinfo().contains(LOCAL_SSD));
    }

    @Test(description = "\"Commitment term:\" section in estimation results should contain local SSD option that have been pointed out")
    public void commitmentTermInEstimationResultsShouldContainSelectedTerm() {
        Assert.assertTrue(estimationResultsPage.readResultsCommitmentTerm().contains(COMMITTED_USAGE));
    }

    @Test(description = "Display estimation cost for comparing with manual-filling result")
    public void provideTotalEstimationCostForVisualControl() {
        System.out.println(estimationResultsPage.readResultsTotalEstimatedCost());
    }

    @AfterClass(alwaysRun = true)
    private void shutDownDriver() {
        driver.quit();
        driver = null;
    }
}
