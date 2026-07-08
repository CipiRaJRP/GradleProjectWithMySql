package com.example.Selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class CheckoutPage extends BasePage{
    private static final By PLACE_ORDER = By.cssSelector("[data-test='place-order']");
    private static final By CONFIRMATION = By.cssSelector("[data-test='order-confirmation'] p:nth-of-type(2)");
    private static final By TOTAL = By.cssSelector("[data-testid ='checkout-total']");

    public String getShipping() {
        return Shipping;
    }

    private  String Shipping = "199";

    public CheckoutPage(WebDriver driver) {
        super(driver);
        visible(PLACE_ORDER);
    }

    public CheckoutPage placeOrder(){
        click(PLACE_ORDER);
        visible(CONFIRMATION);
        return this;
    }

    public String  checkTotal(){
        return text(TOTAL);
    }

    public String confirmationText(){
        return text(CONFIRMATION);
    }

    public boolean isOrderConfirmed() {
        return confirmationText()
                .toLowerCase()
                .contains("confirmed");
    }
}
