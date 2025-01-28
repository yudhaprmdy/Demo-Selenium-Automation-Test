package com.belajarselenium.drivers.strategies;

import com.belajarselenium.drivers.managers.ChromeDriverManager;
import com.belajarselenium.drivers.managers.FirefoxDriverManager;
import com.belajarselenium.drivers.utils.BrowserType;
import org.openqa.selenium.WebDriver;

public class DriverStrategyImplementer {
    public WebDriver setStrategy(String strategy){
        switch (strategy){
            case BrowserType.CHROME:
                return ChromeDriverManager.make();
            case BrowserType.FIREFOX:
                return FirefoxDriverManager.make();
            default:
                return ChromeDriverManager.make();

        }
    }
}
