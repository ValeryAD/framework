package com.github.valeryad.bringiton.test;

import com.github.valeryad.bringiton.page.PastebinHomePage;
import com.github.valeryad.bringiton.page.PastebinIndividualPastePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateNewPasteTest {

    private static final String CODE_FOR_INPUT = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String TITLE = "how to gain dominance among developers";
    private static final String SYNTAX = "Bash";

    private WebDriver driver;
    private PastebinIndividualPastePage individualPastePage;

    @BeforeClass(alwaysRun = true)
    public void browserSetupAndPagePreparation() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        individualPastePage = createNewPaste();
    }

    @Test(description = "check if name/title equals to primal input")
    public void pageHeaderShouldBeEqualToNameTitleInput() {
        Assert.assertEquals(individualPastePage.readPageHeader(), TITLE);
    }

    @Test(description = "check if syntax highlighted for bash")
    public void syntaxShouldBeHighlightedForBash() {
        Assert.assertEquals(individualPastePage.readSyntaxButtonLabel(), SYNTAX);
    }

    @Test(description = "check if code on page equals to primal code")
    public void codeShouldBeEqualToNewPasteInput() {
        Assert.assertEquals(CODE_FOR_INPUT, individualPastePage.readPastedCode());
    }


    private PastebinIndividualPastePage createNewPaste() {
        PastebinHomePage homePage = new PastebinHomePage(driver);
        return homePage.openPage()
                .inputTextIntoPasteInput(CODE_FOR_INPUT)
                .highlightSyntaxFor(SYNTAX)
                .selectExpiration10Minutes()
                .inputTitle(TITLE)
                .savePaste();
    }

    @AfterClass(alwaysRun = true)
    public void browserShutdown() {
        driver.quit();
        driver = null;
    }
}
