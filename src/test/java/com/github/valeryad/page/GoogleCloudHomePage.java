package com.github.valeryad.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage extends AbstractPage {
    private final static String GOOGLE_CLOUD_PAGE_URL = "https://cloud.google.com/";
    private static final String SEARCH_BUTTON_LOCATOR = "//div[@id='devsite-search-popout-container-id-1']//*[contains(text(), 'Все результаты')]";

    @FindBy(xpath = "//div[@class='devsite-search-container']")
    private WebElement searchElement;

    @FindBy(xpath = "//div[@class='devsite-searchbox']/input")
    private WebElement searchInput;


    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(GOOGLE_CLOUD_PAGE_URL);
        return this;
    }

    public GoogleCloudSearchResultsPage searchResultsByTerm(String term) {
        searchElement.click();
        searchInput.sendKeys(term);

        WebElement searchButton = findElementLocatedBy(By
                .xpath(SEARCH_BUTTON_LOCATOR));
        searchButton.click();

        return new GoogleCloudSearchResultsPage(driver);
    }
}
