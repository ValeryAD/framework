package com.github.valeryad.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudEmailEstimatePage extends AbstractPage {

    private static final String BUTTON_ARIA_LABEL_LOCATOR = "//button[@aria-label='Send Email']";

    @FindBy(xpath = "//input[@name='description' and @type='email']")
    private WebElement emailInput;

    public GoogleCloudEmailEstimatePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GoogleCloudEstimateResultsPage openPage(){
        throw new RuntimeException("This method does not supposed to be used in this class." +
                "If developer for some reasons really need to use it, its need to be overridden");
    }

    public void sendEmail(String email) {
        emailInput.sendKeys(email);
        WebElement sendEmailButton = findElementLocatedBy(By.xpath(BUTTON_ARIA_LABEL_LOCATOR));
        sendEmailButton.click();
    }
}
