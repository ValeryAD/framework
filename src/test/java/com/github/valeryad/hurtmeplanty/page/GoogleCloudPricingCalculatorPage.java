package com.github.valeryad.hurtmeplanty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPricingCalculatorPage extends AbstractPage {

    @FindBy(xpath = "//md-tab-item[@class='md-tab ng-scope ng-isolate-scope md-ink-ripple md-active']")
    private WebElement computeEngineSection;

    @FindBy(id = "input_65")
    private WebElement numberOfInstanceInput;

    @FindBy(id = "select_78")
    private WebElement operationSystemSelect;

    @FindBy(id = "select_82")
    private WebElement vmClassSelect;

    @FindBy(xpath = "//md-select[@id='select_90']/..")
    private WebElement seriesSelect;

    @FindBy(id = "select_92")
    private WebElement instanceTypeSelect;

    @FindBy(xpath = "//md-checkbox[contains(@ng-model, '.addGPUs')]")
    private WebElement addGPUsCheckBox;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUSelect;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement typeGPUSelect;

    @FindBy(xpath = "//md-input-container[@class='md-input-has-placeholder md-input-has-value flex']/md-select[@placeholder='Local SSD']")
    private WebElement localSSDSelect;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location' and @ng-model='listingCtrl.computeServer.location']")
    private WebElement dataCenterLocationSelect;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage' and @ng-model='listingCtrl.computeServer.cud']")
    private WebElement committedUsageSelect;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate' and @ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;


    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
        driver.switchTo().frame(0);
        driver.switchTo().frame("myFrame");
    }

    public GoogleCloudPricingCalculatorPage fillNumberOfInstancesField(int numberOfInstances) {
        computeEngineSection.click();
        numberOfInstanceInput.sendKeys(String.valueOf(numberOfInstances));
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectOperatingSystemAndSoftware(String option) {
        operationSystemSelect.click();
        WebElement operationSystemOption = findElementLocatedBy(By
                .xpath(String.format("//div[@id='select_container_79']//md-option[child::div[contains(.,'%s')]]", option)));
        operationSystemOption.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectVMClass(String option) {
        vmClassSelect.click();
        WebElement vmClassOption = findElementLocatedBy(By
                .xpath(String.format("//div[@class='md-select-menu-container md-active md-clickable']//md-option[child::div[contains(text(), '%s')]]", option)));
        vmClassOption.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectSeriesAndInstanceType(String seriesOptionStr, String instanceTypeOptionStr) {
        seriesSelect.click();
        WebElement seriesOption = findElementLocatedBy(By.xpath(String
                .format("//md-option[child::div[contains(.,'%s')]]", seriesOptionStr)));
        seriesOption.click();

        instanceTypeSelect.click();
        WebElement instanceTypeOption = findElementLocatedBy(By.xpath(String
                .format("//md-option/div[contains(.,'%s')]/..", instanceTypeOptionStr)));
        instanceTypeOption.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage addGPUs(int gpusNumber, String gpuOptionStr) {
        addGPUsCheckBox.click();

        numberOfGPUSelect.click();
        WebElement oneGPUOption = findElementLocatedBy(By.xpath(String
                .format("//md-option[div[@class='md-text ng-binding' and text()=%s]]", gpusNumber)));
        oneGPUOption.click();

        typeGPUSelect.click();
        WebElement teslaT4GPUOption = findElementLocatedBy(By.xpath(String
                .format("//md-option[child::div[contains(.,'%s')]]", gpuOptionStr)));
        teslaT4GPUOption.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage addLocalSSD(String option) {
        localSSDSelect.click();
        WebElement ssd2x375option = findElementLocatedBy(By.xpath(String
                .format("//md-option[child::div[@class='md-text ng-binding' and contains(text(),'%s')]]", option)));
        ssd2x375option.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectDataCenterLocation(String option) {
        dataCenterLocationSelect.click();
        WebElement frankfurtLocationOption = findElementLocatedBy(By.xpath(String
                .format("//md-select-menu[@class='md-overflow']//div[contains(text(), '%s')]/..", option)));
        frankfurtLocationOption.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectCommittedUsageTerm(String option) {
        committedUsageSelect.click();
        WebElement oneYearCommittedUsageOption =
                findElementsLocatedBy(By.xpath(String.format("//md-select-menu//div[text()='%s']/..", option))).get(1);
        oneYearCommittedUsageOption.click();
        return this;
    }

    public GoogleCloudEstimationResultsPage estimate() {
        addToEstimateButton.click();
        return new GoogleCloudEstimationResultsPage(driver);
    }
}
