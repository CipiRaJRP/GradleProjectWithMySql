package com.example.Selenium.Record;

import java.time.LocalDate;

public record Order(
        String name,
        int quantity,
        double price,
        String status,
        LocalDate date_on,
        boolean refunded
) {
}