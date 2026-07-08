package com.example.Selenium.pages;

import com.example.Selenium.pages.components.Header;
import com.example.Selenium.support.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public Header header(){
        return new Header(driver);
    }



    public void makeLoginConfiguration(){
        driver.get(Config.baseUrl());
    }

    protected WebElement visible(By by){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected List<WebElement> visibileall(By by){
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected List<WebElement> elements(By by){
        return driver.findElements(by);
    }

    protected void click(By by){

         wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    protected String text(By by){
        return driver.findElement(by).getText();
    }

    protected void staleness(WebElement web,By by){
        wait.until(ExpectedConditions.stalenessOf(web));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by,0));
    }

    protected void spinner(By web,String count){
        wait.until((i)->{
            String currentResultcount = text(web);
            return !currentResultcount.equals("Searching products...")
                    && !currentResultcount.equals(count);
        });
    }

    protected void comparison(By web,String comparingString){
        wait.until(ExpectedConditions.textToBe(web,comparingString));

    }

    protected void urlchecking(String url){
        wait.until(ExpectedConditions.urlContains(url));

    }
    protected void type(By by,CharSequence... text){
        WebElement element = visible(by);
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }
}
