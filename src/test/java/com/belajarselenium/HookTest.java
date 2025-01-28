package com.belajarselenium;

import com.belajarselenium.drivers.DriverSingleton;
import com.belajarselenium.drivers.utils.BrowserType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class HookTest {
    @BeforeTest
    public void init() {
        System.out.println("init");
        DriverSingleton.getDriver(BrowserType.CHROME);
    }

    @AfterTest
    public void teardown() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("teardown");
        DriverSingleton.quitDriver();
    }
}
