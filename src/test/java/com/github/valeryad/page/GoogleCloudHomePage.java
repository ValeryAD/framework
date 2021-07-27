package com.github.valeryad.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudHomePage extends AbstractPage {
    private final static String GOOGLE_CLOUD_PAGE_URL = "https://cloud.google.com/";
    private static final String SEARCH_BUTTON_LOCATOR = "//div[@id='devsite-search-popout-container-id-1']//*[contains(text(), 'Все результаты')] | " +
            "//div[@id='devsite-search-popout-container-id-1']//*[contains(text(), 'All results')]";

    @FindBy(xpath = "//div[@class='devsite-search-container']")
    private WebElement searchElement;

    @FindBy(xpath = "//div[@class='devsite-searchbox']/input")
    private WebElement searchInput;


    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(GOOGLE_CLOUD_PAGE_URL);
        logger.info("Opened Google cloud home page");
        return this;
    }

    public GoogleCloudSearchResultsPage searchResultsByTerm(String term) {
        searchElement.click();
        searchInput.sendKeys(term);

        WebElement searchButton = findElementLocatedBy(By
                .xpath(SEARCH_BUTTON_LOCATOR));
        searchButton.click();
        logger.info(String.format("Search by term \"%s\" has been started", term));
        return new GoogleCloudSearchResultsPage(driver);
    }
}
