package com.example.Selenium.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverFactory implements WebDriverFactory{

    @Override
    public WebDriver createDriver() {
        EdgeOptions options = new EdgeOptions();
        if(Config.headless()){
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1440,900");
        return new EdgeDriver(options);
    }
}
