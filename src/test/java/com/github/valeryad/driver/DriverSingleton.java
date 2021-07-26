package com.github.valeryad.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverSingleton {
    private static WebDriver driver;
    private static Logger logger = LogManager.getRootLogger();
    //TODO dell logger


    public static WebDriver getDriver() {
        if (driver == null) {

            switch (System.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                break;
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    logger.info("height=" + driver.manage().window().getSize().height +
                            "width="+ driver.manage().window().getSize().width);
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
