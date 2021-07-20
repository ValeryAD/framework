package com.github.valeryad.test;

import com.github.valeryad.page.GoogleCloudEstimateResultsPage;
import com.github.valeryad.page.GoogleCloudHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends AbstractTest {

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

    @Test
    public void canGetResultFromCloudCalculator() {
        String result = performEstimateByGoogleCloudCalculator().readEstimatedCost();
        Assert.assertTrue(result != null && result.length() > 0);
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
}
