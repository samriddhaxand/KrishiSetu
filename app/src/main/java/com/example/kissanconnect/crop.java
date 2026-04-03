package com.example.kissanconnect;

public class crop {
    String name, quantity, price, status, freshness;
    int imageRes; // e.g., R.drawable.ic_wheat

    public crop(String name, String quantity, String price, String status, String freshness, int imageRes) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.freshness = freshness;
        this.imageRes = imageRes;
    }
}
