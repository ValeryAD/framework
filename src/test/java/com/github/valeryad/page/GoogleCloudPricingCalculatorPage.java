package com.github.valeryad.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPricingCalculatorPage extends AbstractPage {

    private static final String OS_SOFTWARE_OPTION_LOCATOR_PATTERN = "//md-option[contains(., '%s')]";
    private static final String VM_CLASS_OPTION_LOCATOR_PATTERN = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[contains(., '%s')]";
    private static final String SERIES_OPTION_LOCATOR_PATTERN = "//md-option[child::div[contains(.,'%s')]]";
    private static final String INSTANCE_TYPE_OPTION_LOCATOR_PATTERN = "//md-option/div[contains(.,'%s')]/..";
    private static final String NUMBER_GPU_OPTION_LOCATOR_PATTERN = "//md-option[div[@class='md-text ng-binding' and text()=%s]]";
    private static final String GPU_OPTION__LOCATOR_PATTERN = "//md-option[child::div[contains(.,'%s')]]";
    private static final String LOCAL_SST_OPTION_LOCATOR_PATTERN = "//md-option[child::div[@class='md-text ng-binding' and contains(text(),'%s')]]";
    private static final String LOCATION_OPTION_LOCATOR_PATTERN = "//md-select-menu[@class='md-overflow']//div[contains(text(), '%s')]/..";
    private static final String COMMITTED_USAGE_OPTION_LOCATOR_PATTERN = "//md-select-menu//div[text()='%s']/..";

    @FindBy(xpath = "//md-tab-item[@class='md-tab ng-scope ng-isolate-scope md-ink-ripple md-active']")
    private WebElement computeEngineSection;

    @FindBy(xpath = "//input[@name='quantity' and preceding-sibling::label[contains(text(), 'Number of instances')]]")
    private WebElement numberOfInstanceInput;

    @FindBy(xpath = "//md-select[parent::md-input-container[contains(., 'Operating System / Software')]]")
    private WebElement operationSystemSelect;

    @FindBy(xpath = "//md-select[parent::md-input-container[contains(., 'Machine Class')]]")
    private WebElement vmClassSelect;

    @FindBy(xpath = "//md-select[parent::md-input-container[contains(., 'Series')]]")
    private WebElement seriesSelect;

    @FindBy(xpath = "//md-select[parent::md-input-container[contains(., 'Machine type')]]")
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
        logger.info("Opened google cloud pricing calculator page");
    }

    @Override
    public GoogleCloudEstimateResultsPage openPage() {
        throw new RuntimeException("This method does not supposed to be used in this class." +
                "If developer for some reasons really need to use it, its need to be overridden");
    }


    public GoogleCloudPricingCalculatorPage fillNumberOfInstancesField(int numberOfInstances) {
        computeEngineSection.click();
        numberOfInstanceInput.sendKeys(String.valueOf(numberOfInstances));
        logger.info(String.format("Filling calculator form: number of instances = \"%d\"", numberOfInstances));
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectOSAndSoftware(String option) {
        operationSystemSelect.click();
        WebElement osSoftwareOption = findElementLocatedBy(By
                .xpath(String.format(OS_SOFTWARE_OPTION_LOCATOR_PATTERN, option)));
        osSoftwareOption.click();
        logger.info(String.format("Filling calculator form: selected OS/Software - \"%s\"", option));
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectVMClass(String option) {
        vmClassSelect.click();
        WebElement vmClassOption = findElementLocatedBy(By
                .xpath(String.format(VM_CLASS_OPTION_LOCATOR_PATTERN, option)));
        vmClassOption.click();
        logger.info(String.format("Filling calculator form: selected VM class - \"%s\"", option));
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectSeriesAndInstanceType(String seriesOptionStr, String instanceTypeOptionStr) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", seriesSelect);

        seriesSelect.click();
        WebElement seriesOption = findElementLocatedBy(By.xpath(String
                .format(SERIES_OPTION_LOCATOR_PATTERN, seriesOptionStr)));
        seriesOption.click();

        instanceTypeSelect.click();
        WebElement instanceTypeOption = findElementLocatedBy(By.xpath(String
                .format(INSTANCE_TYPE_OPTION_LOCATOR_PATTERN, instanceTypeOptionStr)));
        instanceTypeOption.click();
        logger.info(String.format("Filling calculator form: selected series - \"%s\" and instance type - \"%s\"",
                seriesOptionStr, instanceTypeOptionStr));
        return this;
    }

    public GoogleCloudPricingCalculatorPage addGPUs(int gpusNumber, String gpuOptionStr) {
        addGPUsCheckBox.click();

        numberOfGPUSelect.click();
        WebElement numberGPUOption = findElementLocatedBy(By.xpath(String
                .format(NUMBER_GPU_OPTION_LOCATOR_PATTERN, gpusNumber)));
        numberGPUOption.click();

        typeGPUSelect.click();
        WebElement gpuOption = findElementLocatedBy(By.xpath(String
                .format(GPU_OPTION__LOCATOR_PATTERN, gpuOptionStr)));
        gpuOption.click();
        logger.info(String.format("Filling calculator form: number of GPUs - \"%d\" and GPU type - \"%s\"",
                gpusNumber, gpuOptionStr));
        return this;
    }

    public GoogleCloudPricingCalculatorPage addLocalSSD(String option) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", localSSDSelect);

        localSSDSelect.click();
        WebElement localSSDOption = findElementLocatedBy(By.xpath(String
                .format(LOCAL_SST_OPTION_LOCATOR_PATTERN, option)));
        localSSDOption.click();
        logger.info(String.format("Filling calculator form: add local SSD - \"%s\"", option));
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectDataCenterLocation(String option) {
        dataCenterLocationSelect.click();
        WebElement LocationOption = findElementLocatedBy(By.xpath(String
                .format(LOCATION_OPTION_LOCATOR_PATTERN, option)));
        LocationOption.click();
        logger.info(String.format("Filling calculator form: select data center location - \"%s\"", option));
        return this;
    }

    public GoogleCloudPricingCalculatorPage selectCommittedUsageTerm(String option) {
        committedUsageSelect.click();
        WebElement committedUsageOption =
                findElementsLocatedBy(By.xpath(String.format(COMMITTED_USAGE_OPTION_LOCATOR_PATTERN, option))).get(1);
        committedUsageOption.click();
        logger.info(String.format("Filling calculator form: select committed usage term - \"%s\"", option));
        return this;
    }

    public GoogleCloudEstimateResultsPage estimate() {
        addToEstimateButton.click();
        logger.info("Filling calculator form: pressed add to estimate button");
        return new GoogleCloudEstimateResultsPage(driver);
    }
}
