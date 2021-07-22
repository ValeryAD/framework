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


    public GoogleCloudEstimateResultsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GoogleCloudEstimateResultsPage openPage(){
        throw new RuntimeException("This method does not supposed to be used in this class." +
                "If developer for some reasons really need to use it, its need to be overridden");
    }

    public String readEstimatedCost() {
        return estimatedCost.getText();
    }

    public GoogleCloudEmailEstimatePage emailEstimate() {
        driver.switchTo().frame(0);
        driver.switchTo().frame(FRAME_NAME);

        emailEstimateButton.click();
        return new GoogleCloudEmailEstimatePage(driver);
    }
}
