package com.example.Selenium.data;



import com.example.Selenium.Record.Order;
import org.openqa.selenium.devtools.latest.network.model.LoaderId;

import java.time.LocalDate;

public class Orderbuilder {
    private String name = "SKU-1";
    private int   quantity = 1;
    private long  price = 1299_00;
    private String status = "NEW";
    private LocalDate date_on = LocalDate.now();
    private boolean refunded = false;

    private Orderbuilder() {
    }

    public static Orderbuilder newOrder(){
        return new Orderbuilder();
    }

    public Orderbuilder withName(String name){
         this.name = name;
         return this;
    }

    public Orderbuilder withQuantity(int quantity){
        this.quantity = quantity;
        return this;
    }

    public Orderbuilder withPrice(long price){
        this.price = price;
        return this;
    }

    public Orderbuilder withStatus(String status){
        this.status = status;
        return this;
    }

    public Orderbuilder withDate(LocalDate date_on){
        this.date_on = date_on;
        return this;
    }

    public Orderbuilder withRefunded(){
        this.status = "REFUNDED";
        this.refunded=true;
        return this;
    }

    public Order build(){
        return new Order(
                name,
                quantity,
                price,
                status,
                date_on,
                refunded
        );
    }
}
