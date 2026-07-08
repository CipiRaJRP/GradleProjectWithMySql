package com.example.Selenium.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeDriverFactory implements WebDriverFactory{
    public ChromeDriverFactory(){}

    @Override
    public  WebDriver createDriver(){
        ChromeOptions options = new ChromeOptions();
        if(Config.headless()){
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1440,900");
        return new ChromeDriver(options);
    }
}
