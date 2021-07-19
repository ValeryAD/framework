package com.github.valeryad.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TenMinutesEmailPage extends AbstractPage {

    private static final String PAGE_URL = "https://10minemail.com/ru/";
    private static final String ATTRIBUTE = "value";
    private static final int TIMEOUT_WAITING_FOR_LETTER = 120;

    @FindBy(id = "mail")
    private WebElement emailLabel;

    public TenMinutesEmailPage(WebDriver driver) {
        super(driver);
    }

    public TenMinutesEmailPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public String readEmail() {
        return emailLabel.getAttribute(ATTRIBUTE);
    }

    public String readEstimatedPrice() {
        WebElement googleLetter = new WebDriverWait(driver, TIMEOUT_WAITING_FOR_LETTER)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By
                                .xpath("//a[contains(text(), 'Google Cloud Platform Price Estimate')]")));
        googleLetter.click();


        WebElement estimatedPrice = findElementLocatedBy(By
                .xpath("//h2[contains(text(), 'Estimated Monthly Cost:')]"));

        return estimatedPrice.getText();
    }


}
