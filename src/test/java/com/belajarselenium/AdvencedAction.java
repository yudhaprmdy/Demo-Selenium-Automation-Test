package com.belajarselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AdvencedAction {
    private WebDriver driver;
    private Actions actions;


    @BeforeClass
    public void setup() {
        // siapkan web driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        actions = new Actions(driver);
    }

    @Test
    public void sigInTest() {
        driver.get("http://localhost:8000/admin");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement usernameFieldElem = driver.findElement(By.id("id_username"));
        WebElement passwordFieldElem = driver.findElement(By.id("id_password"));
        WebElement buttonLoginElem = driver.findElement(By.xpath("//input[@value='Log in']"));

        Action action = actions
                .sendKeys(usernameFieldElem, "yudha").pause(Duration.ofSeconds(1))
                .sendKeys(passwordFieldElem, "123yudha").pause(Duration.ofSeconds(1))
                .moveToElement(buttonLoginElem).pause(Duration.ofSeconds(3)).click().build();

        action.perform();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String expected = "Welcome to Demo SQA Testing Portal";
        String actual = driver.findElement(By.xpath("//h1[normalize-space()='Welcome to Demo SQA Testing Portal']")).getText();

        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 2)
    public void testRightClickAction()  {
        driver.get("http://127.0.0.1:5500/rightclick.html");

        WebElement myDivElem = driver.findElement(By.id("myDiv"));

        Action action = actions.moveToElement(myDivElem)
                .pause(Duration.ofSeconds(2))
                .contextClick(myDivElem)
                .pause(Duration.ofSeconds(2))
                .build();

        action.perform();

//        WebElement messageElemWaiting = driver.findElement(By.id("message"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement messageElemWaiting = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));


        String expected = "Berhasil righ click";
        Assert.assertEquals(messageElemWaiting.getText(), expected);

    }

    @Test(priority = 3)
    public void visibilityTest() {
        driver.navigate().refresh();

        WebElement myDivElem = driver.findElement(By.id("myDiv"));
        WebElement visibilityOfElem = driver.findElement(By.id("visibilityOf"));

        Action action = actions.moveToElement(myDivElem).contextClick(myDivElem).build();
        action.perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(11));
        wait.until(ExpectedConditions.visibilityOf(visibilityOfElem));

        String expected = "My Visibility Of";
        String actual = visibilityOfElem.getText();

        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 4)
    public void osloTest() throws InterruptedException {
        driver.get("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");


        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy({top: 500, behavior: 'smooth'})");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement draggable = driver.findElement(By.id("box1"));
        WebElement droppable = driver.findElement(By.id("box101"));

        Action action = actions.pause(Duration.ofSeconds(1))
                .moveToElement(draggable)
                .pause(Duration.ofSeconds(1))
                .clickAndHold(draggable)
                .pause(Duration.ofSeconds(1))
                .moveToElement(droppable)
//                .pause(Duration.ofSeconds(1))
                .release().build();
        action.perform();

        String backgroundExpected = "rgba(0, 255, 0, 1)";
        String backgroundActual = draggable.getCssValue("background-color");
        Assert.assertEquals(backgroundActual, backgroundExpected);


    }

    @Test(priority = 5)
    public void osloNegativeTest() throws InterruptedException {
        driver.navigate().refresh();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy({top: 500, behavior: 'smooth'})");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement draggable = driver.findElement(By.id("box1"));
        WebElement droppable = driver.findElement(By.id("box103"));

        Action action = actions.pause(Duration.ofSeconds(1))
                .moveToElement(draggable)
                .pause(Duration.ofSeconds(1))
                .clickAndHold(draggable)
                .pause(Duration.ofSeconds(1))
                .moveToElement(droppable)
//                .pause(Duration.ofSeconds(1))
                .release().build();
        action.perform();


        jse.executeScript("document.querySelector('#box1').style.backgroundColor = 'red'");
        String backgroundExpected = "rgba(255, 0, 0, 1)";

        String backgroundActual = driver.findElement(By.id("box1")).getCssValue("background-color");
        System.out.println(backgroundActual);
        Assert.assertTrue(backgroundActual.equals(backgroundExpected));


    }

    public void closing() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        if (driver != null){
            driver.close();
        }
    }
}
