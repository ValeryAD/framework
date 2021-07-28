package com.github.valeryad.waits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class PickedRightOptionCondition implements ExpectedCondition<Boolean> {
    private String locatorPattern;
    private String key;
    private Logger logger;

    public PickedRightOptionCondition(String locatorPattern, String key) {
        this.locatorPattern = locatorPattern;
        this.key = key;
        logger = LogManager.getRootLogger();
    }

    @Override
    public Boolean apply(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.xpath(String.format(locatorPattern, key)));
        if (elements.size() > 0) {
            logger.info(String.format("Wait custom condition: find element by key \"%s\", checking if its right element", key));
            WebElement element = elements.get(elements.size() - 1);
            if (element.isDisplayed() && element.isEnabled() && element.getText().contains(key)) {
                logger.info(String.format("Wait custom condition: find element by key \"%s\"", key));
                return true;
            }
        }
        logger.info(String.format("Wait custom condition: Attempt to find element by key \"%s\" failed", key));
        return false;
    }
}
