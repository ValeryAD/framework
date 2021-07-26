package com.github.valeryad.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TenMinutesEmailPage extends AbstractPage {

    private static final String PAGE_URL = "https://10minemail.com/ru/";
    private static final String ATTRIBUTE = "value";
    private static final int TIMEOUT_WAITING_FOR_LETTER = 40;
    private static final String GOOGLE_LETTER_LINK_LOCATOR = "//a[contains(text(), 'Google Cloud Platform Price Estimate')]";
    private static final String ESTIMATED_PRICE_LABEL_LOCATOR = "//h2[contains(text(), 'Estimated Monthly Cost:')]";

    @FindBy(id = "mail")
    private WebElement emailAddressLabel;

    public TenMinutesEmailPage(WebDriver driver) {
        super(driver);
    }

    public TenMinutesEmailPage openPage() {
        driver.get(PAGE_URL);
        logger.info("Opened ten minutes email page");
        return this;
    }

    public String readEmailAddress() {
        logger.info("Reading email address from ten minutes email page");
        return emailAddressLabel.getAttribute(ATTRIBUTE);
    }

    public String readEstimatedPrice() {
        logger.info("Waiting for receiving email");
        WebElement googleLetterLink = new WebDriverWait(driver, TIMEOUT_WAITING_FOR_LETTER)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .xpath(GOOGLE_LETTER_LINK_LOCATOR)));
        googleLetterLink.click();

        WebElement estimatedPrice = findElementLocatedBy(By
                .xpath(ESTIMATED_PRICE_LABEL_LOCATOR));
        logger.info("Reading estimated price from email");
        return estimatedPrice.getText();
    }
}
