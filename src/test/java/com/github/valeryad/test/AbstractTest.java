package com.github.valeryad.test;

import com.github.valeryad.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AbstractTest {
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    protected void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterClass(alwaysRun = true)
    protected void shutDownBrowser() {
        DriverSingleton.closeDriver();
    }
}
