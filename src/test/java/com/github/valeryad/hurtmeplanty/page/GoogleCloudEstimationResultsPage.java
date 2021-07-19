package com.github.valeryad.hurtmeplanty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudEstimationResultsPage extends AbstractPage {

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


    public GoogleCloudEstimationResultsPage(WebDriver driver) {
        super(driver);
    }

    public String readResultsVMClass() {
        return machineClassLabel.getText();
    }

    public String readResultsInstanceType() {
        return instanceTypeLabel.getText();
    }

    public String readResultsRegion() {
        return regionLabel.getText();
    }

    public String readResultsLocalSSDinfo() {
        return localSSDLabel.getText();
    }

    public String readResultsCommitmentTerm() {
        return commitmentTerm.getText();
    }

    public String readResultsTotalEstimatedCost() {
        return totalEstimatedCostLabel.getText();
    }
}
