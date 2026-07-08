package com.example.Selenium.tests;

import com.example.Selenium.support.ChromeDriverFactory;
import com.example.Selenium.support.EdgeDriverFactory;
import com.example.Selenium.support.FirefoxDriverFactory;
import com.example.Selenium.support.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    void setup() {

        String browser = System.getProperty("browser","chrome");

        WebDriverFactory factory;

        switch (browser.toLowerCase()) {
            case "edge":
                factory = new EdgeDriverFactory();
                break;

            case "firefox":
                factory = new FirefoxDriverFactory();
                break;

            case "chrome":
            default:
                factory = new ChromeDriverFactory();
                break;
        }

        driver = factory.createDriver();
    }


    @AfterEach
    void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}