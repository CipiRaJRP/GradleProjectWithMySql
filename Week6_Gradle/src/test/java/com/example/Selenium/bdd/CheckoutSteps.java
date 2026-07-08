package com.example.Selenium.bdd;

import com.example.Selenium.pages.CatalogPage;
import com.example.Selenium.support.paiseConversion;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutSteps {
    private final World world;

    public CheckoutSteps(World world) {
        this.world = world;
    }

    @Given("the catalog is open")
    public void theCatalogIsOpen(){
          world.catalog=new CatalogPage(world.driver).open();
    }

    @When("I opened a Product {string}")
    public void isOpenedAProduct(String query){
        world.catalog.searchFor(query);
    }


    @And("I add a first result in CartBadge")
    public void iaddTheFirstResultToTheCart(){
        world.product = world.catalog.openFirstProduct();
        world.cart = world.product.addToCart();
    }

    @Then("the CartBadge shows {int}")
    public void theCartShows(int count){
        world.header().cartBadge().expectedCount(count);
    }

    @When("I open a Cart")
    public void isOpenTheCart(){
        world.cart = world.header().opencart();
    }

    @Then("It shows the cart line {int}")
    @Then("it shows cart line as {int}")
    public void isCartHasLineItems(int count){
        assertEquals(count, world.cart.lineCount());
    }

    @When("I place an Order")
    public void iPlaceTheOrder(){
        long SubTotal = paiseConversion.toPaisa(world.cart.total());
        world.checkout = world.cart.proceed();
        long Shipping = paiseConversion.toPaisa(world.checkout.getShipping());
        long Total = paiseConversion.toPaisa(world.checkout.checkTotal());
        world.checkout.placeOrder();

        assertEquals(Total,SubTotal + Shipping);
    }

    @Then("It shows the message Confirmed")
    public void confirmationMessage(){
        assertTrue(world.checkout.isOrderConfirmed());
    }






}
