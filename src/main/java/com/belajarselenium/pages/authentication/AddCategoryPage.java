package com.belajarselenium.pages.authentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AddCategoryPage {

    private WebDriverWait wait;
    private WebDriver driver;

    @FindBy(xpath = "//li[@class='success']")
    WebElement addCategoryTextHeadingElement;

    @FindBy(xpath = "//p[@class='errornote']")
    WebElement errorNoteTextHeadingElement;

    @FindBy(xpath = "//input[@id='id_name']")
    WebElement inputCategoryElement;

    @FindBy(xpath = "//a[normalize-space()='Categorys']")
    WebElement categoryElement;

    @FindBy(xpath = "//a[normalize-space()='Add category']")
    WebElement addCategoryButtonElement;

    @FindBy(xpath = "//input[@name='_save']")
    WebElement saveAddCategoryButtonElement;


    public AddCategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    public void waitForAddCategoryHeadingElement() {
        By locator = By.xpath("//li[@class='success']");
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public boolean getTextAddCategoryHeadingElement() {
        waitForAddCategoryHeadingElement();
        return addCategoryTextHeadingElement.isDisplayed();
    }



    public void waitForErrorNoteTextHeadingElement(){
        By locator = By.xpath("//p[@class='errornote']");
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String getTextErrorNoteTextHeadingElement(){
        waitForErrorNoteTextHeadingElement();
        return errorNoteTextHeadingElement.getText();
    }

    public void clickCategory(){
        categoryElement.click();
    }

    public void fillAddCategory(String addCategory){
        inputCategoryElement.sendKeys(addCategory);
    }

    public void clickAddCategoryButton(){
        addCategoryButtonElement.click();
    }

    public void clickSaveButtonAddCategory(){
        saveAddCategoryButtonElement.click();
    }

    public void addCategoryActivity(String addCategory) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        clickCategory();
        TimeUnit.SECONDS.sleep(1);
        clickAddCategoryButton();
        TimeUnit.SECONDS.sleep(1);
        fillAddCategory(addCategory);
        TimeUnit.SECONDS.sleep(1);
        clickSaveButtonAddCategory();
    }

    public void addCategoryActivityNegative() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        clickCategory();
        TimeUnit.SECONDS.sleep(1);
        clickAddCategoryButton();
        TimeUnit.SECONDS.sleep(1);
        clickSaveButtonAddCategory();
    }


}
