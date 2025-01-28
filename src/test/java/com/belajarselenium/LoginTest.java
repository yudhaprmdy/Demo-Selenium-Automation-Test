package com.belajarselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    private  WebDriver driver;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        driver.get("http://localhost:8000/admin");
    }

    @Test
    public void loginTest() throws InterruptedException {
        WebElement fieldUserName = driver.findElement(By.id("id_username"));
        WebElement fieldPassword = driver.findElement(By.id("id_password"));
        WebElement btnLogin = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[3]/input"));

        TimeUnit.SECONDS.sleep(1);
        fieldUserName.sendKeys("yudha");
        TimeUnit.SECONDS.sleep(1);
        fieldPassword.sendKeys("123yudha");
        TimeUnit.SECONDS.sleep(1);
        btnLogin.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String expected = "Welcome to Demo SQA Testing Portal";
        String actual = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

        Assert.assertEquals(expected,actual);
    }

    @AfterClass
    public void closing() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        if (driver != null){
            driver.close();
        }
    }
}
