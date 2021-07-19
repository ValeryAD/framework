package com.github.valeryad.icanwin.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinHomePage {
    private static final String PASTEBIN_HOME_PAGE_URL = "https://pastebin.com/";

    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement pasteInput;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement selectExpiration;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement selectExpiration10MinutesOption;

    @FindBy(id = "postform-name")
    private WebElement nameTitleInput;

    @FindBy(xpath = "//button[@class='btn -big' and @type='submit']")
    private WebElement createPasteButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(PASTEBIN_HOME_PAGE_URL);
        return this;
    }

    public PastebinHomePage inputTextIntoPasteInput(String text) {
        pasteInput.sendKeys(text);
        return this;
    }

    public PastebinHomePage selectExpiration10Minutes() {
        selectExpiration.click();
        selectExpiration10MinutesOption.click();
        return this;
    }


    public PastebinHomePage pasteTitle(String title) {
        nameTitleInput.sendKeys(title);
        return this;
    }

    public void savePaste() {
        createPasteButton.click();
    }
}
