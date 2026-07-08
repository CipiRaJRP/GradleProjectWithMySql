package com.example.Selenium.pages.components;

import com.example.Selenium.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCard  {
    private static final By PRODUCT_TITLE = By.cssSelector("[data-test='product-title']");
    private static final By PRODUCT_PRICE = By.cssSelector("[data-test='product-price']");

    private final WebElement root;

    public ProductCard(WebElement root){
        this.root=root;
    }

    public String title(){
        return root.findElement(PRODUCT_TITLE).getText();
    }

    public int price(){
        return Integer.parseInt(root.findElement(PRODUCT_PRICE).getText().replaceAll("[^0-9]",""));
    }
}
