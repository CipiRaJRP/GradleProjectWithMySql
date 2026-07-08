package com.example.Selenium.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory implements WebDriverFactory{

    @Override
    public WebDriver createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if(Config.headless()){
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1440,900");
        return new FirefoxDriver(options);
    }
}
