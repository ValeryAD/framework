package com.github.valeryad.test;

import com.github.valeryad.page.GoogleCloudEstimateResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends AbstractTest {
    GoogleCloudEstimateResultsPage estimateResultsPage;

    @Test(alwaysRun = true, description = "smoke test: fill form of google price calculator and check if there's any result")
    public void canGetResultFromCloudCalculator() {
        estimateResultsPage = performEstimateByGoogleCloudCalculator();

        String result = estimateResultsPage.readEstimatedCost();
        Assert.assertTrue(result != null && result.length() > 0);
    }
}
