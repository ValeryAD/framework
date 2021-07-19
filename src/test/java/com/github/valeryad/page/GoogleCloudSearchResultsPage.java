package com.github.valeryad.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudSearchResultsPage extends AbstractPage {


    public GoogleCloudSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPricingCalculatorPage selectPricingCalculatorResult(String searchTerm) {

        WebElement pricingCalculatorResult = new WebDriverWait(driver, COMMON_TIMEOUT).
                until(ExpectedConditions.presenceOfElementLocated(By.
                        xpath(String.format("//div[@class='gs-title']/a[child::b[text()='%1$s']] |" +
                                " //a[@class='gs-title' and text()='%1$s']", searchTerm))));
        pricingCalculatorResult.click();

        return new GoogleCloudPricingCalculatorPage(driver);
    }
}
