package com.example.Selenium.pages;

import com.example.Selenium.support.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private static final By SIGN_IN = By.className("primary");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open(){
        driver.get(Config.baseUrl());
        click(SIGN_IN);
        return new LoginPage(driver);
    }
}
