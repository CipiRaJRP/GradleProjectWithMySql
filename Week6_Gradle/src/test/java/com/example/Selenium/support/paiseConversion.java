package com.example.Selenium.support;

public class paiseConversion {
    public static long toPaisa(String amount) {
        return Long.parseLong(
                amount.trim()
                        .replaceAll("[₹|Rs,\\s]", "")
                        .replace(".", "")
        );
    }
}
