package com.github.valeryad.test;

import com.github.valeryad.page.GoogleCloudEstimateResultsPage;
import com.github.valeryad.page.TenMinutesEmailPage;
import com.github.valeryad.util.StringUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ResultByEmailTest extends AbstractTest {
    private static final double DELTA = 0.01;

    @Test(description = "Results received from email and google calculator should be equal")
    public void emailAndGoogleCalcResultsShouldBeEqual() {
        GoogleCloudEstimateResultsPage estimateResultsPage = performEstimateByGoogleCloudCalculator();

        String estimatedCostFromCalculator = estimateResultsPage.readEstimatedCost();
        String estimatedCostReceivedByEmail = receiveEstimationCostByEmail(estimateResultsPage);

        Assert.assertEquals(StringUtil.getPriceFromEstimatedCostString(estimatedCostFromCalculator),
                StringUtil.getPriceFromEstimatedCostString(estimatedCostReceivedByEmail), DELTA);
    }


    public String receiveEstimationCostByEmail(GoogleCloudEstimateResultsPage resultsPage) {

        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
        String googleCloudHandle = String.valueOf(tabList.get(0));
        String tenMinMailHandle = String.valueOf(tabList.get(1));
        driver.switchTo().window(tenMinMailHandle);

        TenMinutesEmailPage tenMinMailPage = new TenMinutesEmailPage(driver);
        tenMinMailPage.openPage();
        String email = tenMinMailPage.readEmailAddress();

        driver.switchTo().window(googleCloudHandle);

        resultsPage.emailEstimate()
                .sendEmail(email);

        driver.switchTo().window(tenMinMailHandle);

        return tenMinMailPage.readEstimatedPrice();
    }
}
