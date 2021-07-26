package com.github.valeryad.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleCloudSearchResultsPage extends AbstractPage {

    private static final String PRICING_CALCULATOR_SEARCH_RESULT_LOCATOR_PATTERN = "//div[@class='gs-title']/a[child::b[text()='%1$s']] |" +
            " //a[@class='gs-title' and text()='%1$s']";

    public GoogleCloudSearchResultsPage(WebDriver driver) {
        super(driver);
        logger.info("Opened google cloud search results page");
    }

    @Override
    public GoogleCloudEstimateResultsPage openPage() {
        throw new RuntimeException("This method does not supposed to be used in this class." +
                "If developer for some reasons really need to use it, its need to be overridden");
    }

    public GoogleCloudPricingCalculatorPage selectPricingCalculatorResult(String searchTerm) {
        WebElement pricingCalculatorSearchResult = findElementLocatedBy(By
                .xpath(String.format(PRICING_CALCULATOR_SEARCH_RESULT_LOCATOR_PATTERN, searchTerm)));
        pricingCalculatorSearchResult.click();
        logger.info(String.format("Picked %s link from results", searchTerm));
        return new GoogleCloudPricingCalculatorPage(driver);
    }
}
