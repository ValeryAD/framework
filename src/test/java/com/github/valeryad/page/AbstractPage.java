package com.github.valeryad.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbstractPage {
    protected static final int COMMON_TIMEOUT = 10;
    protected WebDriver driver;

    protected Logger logger = LogManager.getRootLogger();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected abstract AbstractPage openPage();

    public WebElement findElementLocatedBy(By by) {
        return new WebDriverWait(driver, COMMON_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> findElementsLocatedBy(By by) {
        return new WebDriverWait(driver, COMMON_TIMEOUT)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
}
