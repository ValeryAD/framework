package com.github.valeryad.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class DriverSingleton {
    private static WebDriver driver;
    private static Logger logger = LogManager.getRootLogger();

    public static WebDriver getDriver() {
        if (driver == null) {

            switch (System.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions()
                            .addArguments("--headless", "--width=1366", "--height=1536");
                    driver = new FirefoxDriver(firefoxOptions);
                    logger.info(String.format("window-size=%dx%d",
                            driver.manage().window().getSize().width,
                            driver.manage().window().getSize().height));
                }
                break;
                default: {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions()
                            .addArguments("--headless", "window-size=1366,1536");
                    driver = new ChromeDriver(chromeOptions);
                    logger.info(String.format("window-size=%dx%d",
                            driver.manage().window().getSize().width,
                            driver.manage().window().getSize().height));
                    /*driver = new ChromeDriver();
                    driver.manage().window().maximize();*/
                }
            }
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
