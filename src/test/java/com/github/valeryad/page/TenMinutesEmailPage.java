package com.github.valeryad.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class TenMinutesEmailPage extends AbstractPage {

    private static final String PAGE_URL = "https://dropmail.me/en/";
    private static final int TIMEOUT_WAITING_FOR_LETTER = 60;
    private static final String GOOGLE_LETTER_LINK_LOCATOR = "//dd[contains(text(), 'Google Cloud Platform Price Estimate')]";
    private static final String ESTIMATED_PRICE_LABEL_LOCATOR = "//h2[contains(text(), 'Estimated Monthly Cost')]";

    @FindBy(xpath = "//span[@class='address']")
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
        String emailAddress = emailAddressLabel.getText();
        logger.info(String.format("Reading email address from ten minutes email page:", emailAddress));
        return emailAddress;
    }

    public String readEstimatedPrice() {
        WebElement googleLetterLink = new WebDriverWait(driver, TIMEOUT_WAITING_FOR_LETTER)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(GOOGLE_LETTER_LINK_LOCATOR)));

        findElementsLocatedBy(By
                .xpath("//pre[contains(text(), 'Cloud Pricing Calculator')]/../following-sibling::a[@class='btn btn-default btn-sm']"))
                .get(0).click();

        driver.switchTo().parentFrame();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='mail-clean-html']")));

        String estimatedPrice = findElementsLocatedBy(By
                .xpath(ESTIMATED_PRICE_LABEL_LOCATOR)).get(0).getText();
        logger.info("Estimated result from email = " + estimatedPrice);
        return estimatedPrice;
    }
}
