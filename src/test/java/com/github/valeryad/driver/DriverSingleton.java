package com.github.valeryad.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {
    private static WebDriver driver;
    private static final String RESOURCES_PATH = "src\\test\\resources\\";

    public WebDriver getDriver() {
        if (driver == null) {
            switch (System.getProperty("browser")) {
                case "firefox": {
                    System.setProperty("webdriver.gecko.driver", RESOURCES_PATH + "geckodriver.exe");
                    driver = new FirefoxDriver();
                }
                break;
                default: {
                    System.setProperty("webdriver.chrome.driver", RESOURCES_PATH + "chromedriver.exe");
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public void closeDriver() {
        driver.quit();
        driver = null;
    }
}
