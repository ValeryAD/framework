package com.github.valeryad.bringiton.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinIndividualPastePage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='info-bar']/div[@class='info-top']/h1[text()]")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='top-buttons']/div[@class='left']/a")
    private WebElement syntaxLabel;

    @FindBy(xpath = "//textarea[@class='textarea']")
    private WebElement textArea;

    public PastebinIndividualPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String readPageHeader() {
        return pageHeader.getText();
    }

    public String readPastedCode() {
        return textArea.getText();
    }

    public String readSyntaxButtonLabel() {
        return syntaxLabel.getText();
    }


}
