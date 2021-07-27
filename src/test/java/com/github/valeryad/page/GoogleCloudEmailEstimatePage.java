package com.github.valeryad.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleCloudEmailEstimatePage extends AbstractPage {

    private static final String BUTTON_ARIA_LABEL_LOCATOR = "//button[@aria-label='Send Email']";

    public GoogleCloudEmailEstimatePage(WebDriver driver) {
        super(driver);
        logger.info("Opened google cloud email estimate page");
    }

    @Override
    public GoogleCloudEstimateResultsPage openPage() {
        throw new RuntimeException("This method does not supposed to be used in this class." +
                "If developer for some reasons really need to use it, its need to be overridden");
    }

    public void sendEmail(String email) {
        WebElement emailInput = findElementLocatedBy(By.xpath("//input[@name='description' and @type='email']"));
        emailInput.sendKeys(email);
        WebElement sendEmailButton = findElementLocatedBy(By.xpath(BUTTON_ARIA_LABEL_LOCATOR));
        sendEmailButton.click();
        logger.info(String.format("Email with estimate result has been sent to: %s", email));
    }
}
