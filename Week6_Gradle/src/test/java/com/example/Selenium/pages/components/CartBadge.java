package com.example.Selenium.pages.components;

import com.example.Selenium.pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartBadge {

    private static final By COUNT =By.cssSelector("[data-test='cart-count']");

    private final WebDriverWait wait;

    public CartBadge(WebDriverWait wait){
        this.wait = wait;
    }

    public int count(){
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(COUNT)).getText());
    }

    public void expectedCount(int expectedCount){
        wait.until(ExpectedConditions.textToBe(COUNT,String.valueOf(expectedCount)));
    }


}
