package com.example.Selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends  BasePage{
    private static final By TITLE = By.id("login-title");

    protected LoginPage(WebDriver driver) {
        super(driver);
    }

    public String validateTitle(){
        return text(TITLE);
    }
}
