package com.example.Selenium.tests;

import com.example.Selenium.Record.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.example.Selenium.data.Orderbuilder.newOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderStructureTest {

    @Test
    void builderBySensibleDefaults(){
        Order order = newOrder().build();

        assertEquals("SKU-1",order.name());
        assertEquals(1,order.quantity());
        assertEquals(1299_00,order.price());
        assertEquals("NEW",order.status());
        assertEquals(LocalDate.now(),order.date_on());
        assertEquals(false,order.refunded());
    }

    @Test
    void builderWithDynamicValues(){
        Order order = newOrder().withQuantity(3).build();

        assertEquals(3,order.quantity());
        assertEquals("SKU-1",order.name());
        assertEquals("NEW",order.status());
    }
}