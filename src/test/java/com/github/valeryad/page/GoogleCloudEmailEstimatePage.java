package com.github.valeryad.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudEmailEstimatePage extends AbstractPage {

    @FindBy(xpath = "//input[@name='description' and @type='email']")
    private WebElement emailInput;

    public GoogleCloudEmailEstimatePage(WebDriver driver) {
        super(driver);
    }

    public void sendEmail(String email) {
        emailInput.sendKeys(email);
        WebElement sendEmailButton = findElementLocatedBy(By.xpath("//button[@aria-label='Send Email']"));
        sendEmailButton.click();
    }
}
