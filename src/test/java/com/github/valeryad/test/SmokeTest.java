package com.github.valeryad.test;

import com.github.valeryad.page.GoogleCloudEstimateResultsPage;
import com.github.valeryad.page.GoogleCloudHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends AbstractTest {


    @Test
    public void canGetResultFromCloudCalculator() {
        String result = resultsPage.readEstimatedCost();
        Assert.assertTrue(result != null && result.length() > 0);
    }
}
