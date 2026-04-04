package com.example.kissanconnect;

public class Product {
    private String name, farmer, price;
    private int imageRes;

    public Product(String name, String farmer, String price, int imageRes) {
        this.name = name;
        this.farmer = farmer;
        this.price = price;
        this.imageRes = imageRes;
    }

    public String getName() { return name; }
    public String getFarmer() { return farmer; }
    public String getPrice() { return price; }
    public int getImageRes() { return imageRes; }
}
