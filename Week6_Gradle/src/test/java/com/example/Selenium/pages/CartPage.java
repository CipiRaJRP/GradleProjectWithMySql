package com.example.Selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final By CART_PAGE = By.cssSelector("[data-test='cart-page']");
    private static final By LINES = By.cssSelector("[data-test='cart-line']");
    private static final By TOTAL = By.cssSelector("[data-test='cart-total']");
    private static final By CHECKOUT = By.cssSelector("[data-test='checkout-button']");
    private static final By PRODUCT_ICON = By.cssSelector(".main-nav a[href='/catalog']");


    public CartPage(WebDriver driver) {
        super(driver);
        visible(CART_PAGE);
    }

    public int lineCount(){
        return elements(LINES).size();
    }

    public String total(){
        return text(TOTAL);
    }

    public CheckoutPage proceed(){
        click(CHECKOUT);
        return new CheckoutPage(driver);
    }


    public CatalogPage switchBackToCatalog(){
        click(PRODUCT_ICON);
        return new CatalogPage(driver);
    }


}
