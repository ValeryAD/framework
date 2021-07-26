package com.github.valeryad.test;

import com.github.valeryad.page.GoogleCloudEstimateResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends AbstractTest {
    GoogleCloudEstimateResultsPage estimateResultsPage;

    @Test
    public void canGetResultFromCloudCalculator() {
        estimateResultsPage = performEstimateByGoogleCloudCalculator();

        String result = estimateResultsPage.readEstimatedCost();
        Assert.assertTrue(result != null && result.length() > 0);
    }
}
