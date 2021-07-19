package com.github.valeryad.icanwin.test;

import com.github.valeryad.icanwin.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PastebinTest {

    private static final String TEXT_FOR_INPUT = "Hello from WebDriver";
    private static final String TITLE = "helloweb";

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Creating new paste")
    public void canCreatePaste() {

        PastebinHomePage homePage = new PastebinHomePage(driver);
        homePage.openPage()
                .inputTextIntoPasteInput(TEXT_FOR_INPUT)
                .selectExpiration10Minutes()
                .pasteTitle(TITLE)
                .savePaste();

        try {
            TimeUnit.MILLISECONDS.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @AfterMethod(alwaysRun = true)
    public void browserShutdown() {
        driver.quit();
        driver = null;
    }
}
