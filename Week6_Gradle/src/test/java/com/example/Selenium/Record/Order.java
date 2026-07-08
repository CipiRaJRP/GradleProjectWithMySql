package com.example.Selenium.Record;

import java.time.LocalDate;

public record Order(
        String name,
        int quantity,
        long price,
        String status,
        LocalDate date_on,
        boolean refunded
) {
}