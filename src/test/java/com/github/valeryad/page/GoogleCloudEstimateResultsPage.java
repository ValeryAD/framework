package com.github.valeryad.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudEstimateResultsPage extends AbstractPage {

    @FindBy(id = "email_quote")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//b[contains(text(), 'Total Estimated Cost:')]")
    private WebElement estimatedCost;


    public GoogleCloudEstimateResultsPage(WebDriver driver) {
        super(driver);
    }

    public String readEstimatedCost() {
        return estimatedCost.getText();
    }

    public GoogleCloudEmailEstimatePage emailEstimate() {
        driver.switchTo().frame(0);
        driver.switchTo().frame("myFrame");

        emailEstimateButton.click();
        return new GoogleCloudEmailEstimatePage(driver);
    }
}
