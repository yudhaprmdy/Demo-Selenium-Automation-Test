package com.belajarselenium.authentication;

import com.belajarselenium.drivers.DriverSingleton;
import com.belajarselenium.drivers.utils.BrowserType;
import com.belajarselenium.pages.authentication.AddCategoryPage;
import com.belajarselenium.pages.authentication.DashboardPage;
import com.belajarselenium.pages.authentication.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInTestAdmin {
    private SignInPage signInPage;
    private DashboardPage dashboardPage;

    private AddCategoryPage addCategoryPage;
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverSingleton.driver;
    }

    @Test
    public void signInTest() throws InterruptedException {
        driver.get("http://localhost:8000/admin");

        // SIGNIN TEST
        signInPage = new SignInPage(driver);
        signInPage.loginActivity("yudha", "123yudha");

        //DASHBOARD PAGE TEST
        dashboardPage = new DashboardPage(driver);

        String expected = "Welcome to Demo SQA Testing Portal";
        String actual = dashboardPage.getTextWelocmeHeadingElement();
        Assert.assertEquals(actual.toLowerCase(), expected.toLowerCase());

        //CATEGORY PAGE TEST
        addCategoryPage = new AddCategoryPage(driver);

        addCategoryPage.addCategoryActivity("Komputer");
        Assert.assertTrue(addCategoryPage.getTextAddCategoryHeadingElement());

        addCategoryPage.addCategoryActivityNegative();
        String expectedErrorAddCat = "Please correct the error below.";
        String actualErrorAddCat = addCategoryPage.getTextErrorNoteTextHeadingElement();
        Assert.assertEquals(actualErrorAddCat.toLowerCase(), expectedErrorAddCat.toLowerCase());



    }
}
