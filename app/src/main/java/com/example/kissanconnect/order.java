package com.example.kissanconnect;

import java.util.List;

public class order {
    String customerName;
    String location;
    String status;
    String orderId;
    String totalAmount;
    List<String> items; // e.g., ["Tomatoes 15kg", "Onions 10kg"]

    public order(String name, String loc, String status, String id, String amount, List<String> items) {
        this.customerName = name;
        this.location = loc;
        this.status = status;
        this.orderId = id;
        this.totalAmount = amount;
        this.items = items;
    }
}
