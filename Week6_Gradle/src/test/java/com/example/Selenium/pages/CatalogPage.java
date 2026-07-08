package com.example.Selenium.pages;

import com.example.Selenium.pages.components.ProductCard;
import com.example.Selenium.support.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CatalogPage extends BasePage{
    private static final By SEARCH = By.cssSelector("[data-test='search-input']");
    private static final By RESULT = By.cssSelector("[data-test='catalog-result-count']");
    private static final By CARDS = By.cssSelector("[data-test='product-card']");
    private static final By SORT = By.cssSelector("[data-test='sort-select']");
    private static final By EMPTY= By.cssSelector("[data-test='empty-search']");
    private static final By FIRST_LINK = By.cssSelector("[data-test='product-card'] a");


    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public  CatalogPage open(){

        driver.get(Config.Catalog());
        waitForPageLoad();
        return this;

    }

    public void waitForPageLoad(){
        visible(SEARCH);
        visible(RESULT);
    }

    public CatalogPage searchFor( String query){
        String previousResultcount = text(RESULT);
        System.out.println(previousResultcount);
        type(SEARCH,query);
        spinner(RESULT,previousResultcount);
        return this;
    }

    public CatalogPage searchFor(String query,String expectedResultcount){
        searchFor(query);
        comparison(RESULT,expectedResultcount);
        return this;
    }

    public CatalogPage sortBy(String visbiletext){
        WebElement oldfirstcard=visible(CARDS);
        new Select(visible(SORT)).selectByVisibleText(visbiletext);
        staleness(oldfirstcard,CARDS);

        return this;
    }

    public List<ProductCard> cards(){
        return visibileall(CARDS).stream()
                .map(ProductCard::new)
                .toList();
    }

    public List<String> titles(){
        return cards().stream()
                .map(ProductCard::title)
                .toList();
    }

    public List<Integer> prices(){
        return cards().stream()
                .map(ProductCard::price)
                .toList();
    }

    public String emptySearchMessage(){
        return text(EMPTY);
    }

    public ProductPage openFirstProduct(){
        click(FIRST_LINK);
        return new ProductPage(driver);
    }


}
