package com.github.valeryad.test;

import com.github.valeryad.driver.DriverSingleton;
import com.github.valeryad.model.Machine;
import com.github.valeryad.page.GoogleCloudEstimateResultsPage;
import com.github.valeryad.page.GoogleCloudHomePage;
import com.github.valeryad.service.MachineCreator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class AbstractTest {
    private final static String SEARCH_TERM = "Google Cloud Platform Pricing Calculator";

    protected WebDriver driver;
    protected Machine machine;
    protected GoogleCloudEstimateResultsPage resultsPage;

    @BeforeClass(alwaysRun = true)
    protected void setUp() {
        driver = DriverSingleton.getDriver();
        machine = MachineCreator.withParametersFromProperty();
        resultsPage = performEstimateByGoogleCloudCalculator();
    }

    private GoogleCloudEstimateResultsPage performEstimateByGoogleCloudCalculator() {
        return new GoogleCloudHomePage(driver)
                .openPage()
                .searchResultsByTerm(SEARCH_TERM)
                .selectPricingCalculatorResult(SEARCH_TERM)
                .fillNumberOfInstancesField(machine.getNumberInstances())
                .selectOSAndSoftware(machine.getOsAndSoftware())
                .selectVMClass(machine.getVmClass())
                .selectSeriesAndInstanceType(machine.getSeries(), machine.getType())
                .addGPUs(machine.getNumberGPUs(), machine.getGpuType())
                .addLocalSSD(machine.getLocalSSD())
                .selectDataCenterLocation(machine.getRegion())
                .selectCommittedUsageTerm(machine.getCommittedUsage())
                .estimate();
    }

    @AfterClass(alwaysRun = true)
    protected void shutDownBrowser() {
        DriverSingleton.closeDriver();
    }
}
