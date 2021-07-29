package com.github.valeryad.waits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.regex.Pattern;

public class ElementContainsEmailAddressCondition implements ExpectedCondition<Boolean> {
    private Pattern pattern;
    private By by;
    private Logger logger = LogManager.getRootLogger();

    public ElementContainsEmailAddressCondition(By by) {
        this.by = by;
        pattern = Pattern.compile("^.+@.+\\.\\w{2,4}$");
    }

    @Override
    public Boolean apply(WebDriver driver) {
        Boolean result = pattern.matcher(driver.findElement(by).getAttribute("value")).matches();
        logger.info("Wait custom condition: has red email? - " + result);
        return result;
    }
}
