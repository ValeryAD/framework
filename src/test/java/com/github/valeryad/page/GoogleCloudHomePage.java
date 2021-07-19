package com.github.valeryad.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudHomePage extends AbstractPage {
    private final static String GOOGLE_CLOUD_PAGE_URL = "https://cloud.google.com/";

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
        searchInput.sendKeys(term + "\n");
        return new GoogleCloudSearchResultsPage(driver);
    }
}
