package com.example.Selenium.data;

import com.example.Selenium.repository.OrderRepository;

public class OrderFactory {
    private final OrderRepository repository;

    public OrderFactory(OrderRepository repository) {
        this.repository = repository;
    }

    public long persisted(Orderbuilder builder){
        return repository.save(builder.build());
    }
}
