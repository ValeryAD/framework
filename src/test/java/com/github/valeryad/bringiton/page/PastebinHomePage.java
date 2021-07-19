package com.github.valeryad.bringiton.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage {
    private static final String PASTEBIN_HOME_PAGE_URL = "https://pastebin.com/";
    private static final int TIMEOUT = 10;

    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement pasteInput;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpirationSelect;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement expirationSelect10MinutesOption;

    @FindBy(xpath = "//span[text()='Syntax Highlighting']/../div[@class='toggle__control']")
    private WebElement syntaxHighlightingCheckBox;

    @FindBy(id = "select2-postform-format-container")
    private WebElement syntaxHighlightingSelect;

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

    public PastebinHomePage highlightSyntaxFor(String syntax) {
        syntaxHighlightingCheckBox.click();
        syntaxHighlightingSelect.click();
        WebElement option = new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(String
                                .format("//li[text()='%s']", syntax))));
        option.click();
        return this;
    }

    public PastebinHomePage selectExpiration10Minutes() {
        pasteExpirationSelect.click();
        expirationSelect10MinutesOption.click();
        return this;
    }


    public PastebinHomePage inputTitle(String title) {
        nameTitleInput.sendKeys(title);
        return this;
    }

    public PastebinIndividualPastePage savePaste() {
        createPasteButton.click();
        return new PastebinIndividualPastePage(driver);
    }
}
