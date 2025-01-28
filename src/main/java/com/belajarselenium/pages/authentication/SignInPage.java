package com.belajarselenium.pages.authentication;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class SignInPage {
    private WebDriver driver;

    @FindBy(id = "id_username")
    WebElement usernameElement;

    @FindBy(id = "id_password")
    WebElement passwordElement;

    @FindBy(xpath = "//input[@value='Log in']")
    WebElement buttonSignInElement;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillUsername(String username) {
        usernameElement.sendKeys(username);
    }

    public void fillPassword(String password) {
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        buttonSignInElement.click();
    }

    public void loginActivity(String username, String password) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        fillUsername(username);
        TimeUnit.SECONDS.sleep(1);
        fillPassword(password);
        TimeUnit.SECONDS.sleep(1);
        clickLoginButton();
    }
}
