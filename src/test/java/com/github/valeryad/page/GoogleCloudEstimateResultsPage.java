package com.github.valeryad.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudEstimateResultsPage extends AbstractPage {

    private static final String FRAME_NAME = "myFrame";

    @FindBy(id = "email_quote")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//b[contains(text(), 'Total Estimated Cost:')]")
    private WebElement estimatedCost;

    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(),'VM class:')]")
    private WebElement machineClassLabel;

    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Instance type')]")
    private WebElement instanceTypeLabel;

    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Region')]")
    private WebElement regionLabel;

    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'local SSD')]")
    private WebElement localSSDLabel;


    @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(.,'Commitment term:')]")
    private WebElement commitmentTerm;

    @FindBy(xpath = "//b[@class='ng-binding' and contains(text(), 'Total Estimated Cost')]")
    private WebElement totalEstimatedCostLabel;


    public GoogleCloudEstimateResultsPage(WebDriver driver) {
        super(driver);
        logger.info("Opened google cloud estimate search result page");
    }

    @Override
    public GoogleCloudEstimateResultsPage openPage() {
        throw new RuntimeException("This method does not supposed to be used in this class." +
                "If developer for some reasons really need to use it, its need to be overridden");
    }

    public String readResultsVMClass() {
        logger.info("Reading calculator results: vm class");
        return machineClassLabel.getText();
    }

    public String readResultsInstanceType() {
        logger.info("Reading calculator results: InstanceType");
        return instanceTypeLabel.getText();
    }

    public String readResultsRegion() {
        logger.info("Reading calculator results: region");
        return regionLabel.getText();
    }

    public String readResultsLocalSSDinfo() {
        logger.info("Reading calculator results: local SSD");
        return localSSDLabel.getText();
    }

    public String readResultsCommitmentTerm() {
        logger.info("Reading calculator results: Commitment term");
        return commitmentTerm.getText();
    }

    public String readEstimatedCost() {
        logger.info("Reading calculator results: estimated cost");
        return estimatedCost.getText();
    }

    public GoogleCloudEmailEstimatePage emailEstimate() {
        driver.switchTo().frame(0);
        driver.switchTo().frame(FRAME_NAME);
        emailEstimateButton.click();
        logger.info("button \"email estimate\" has been pressed");
        return new GoogleCloudEmailEstimatePage(driver);
    }
}
