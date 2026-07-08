package com.example.Selenium.bdd;

import com.example.Selenium.pages.CartPage;
import com.example.Selenium.pages.CatalogPage;
import com.example.Selenium.pages.CheckoutPage;
import com.example.Selenium.pages.ProductPage;
import com.example.Selenium.pages.components.Header;
import org.openqa.selenium.WebDriver;

public class World {
    public WebDriver driver;
    public CatalogPage catalog;
    public ProductPage product;
    public CartPage cart;
    public CheckoutPage checkout;

    public Header header(){
        return new Header(driver);
    }
}
