package com.github.valeryad.hardcore.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudEmailEstimatePage extends AbstractPage {

    @FindBy(xpath = "//input[@name='description' and @type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    public GoogleCloudEmailEstimatePage(WebDriver driver) {
        super(driver);
    }

    public void sendEmail(String email) {
        emailInput.sendKeys(email);
        sendEmailButton.click();
    }
}
