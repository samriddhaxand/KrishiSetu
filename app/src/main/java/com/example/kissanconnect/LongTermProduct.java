package com.example.kissanconnect;

public class LongTermProduct {
    private String name;
    private String farmer;
    private String price;
    private String discount; // e.g., "10% OFF"
    private int imageResource;

    public LongTermProduct(String name, String farmer, String price, String discount, int imageResource) {
        this.name = name;
        this.farmer = farmer;
        this.price = price;
        this.discount = discount;
        this.imageResource = imageResource;
    }

    // Getters
    public String getName() { return name; }
    public String getFarmer() { return farmer; }
    public String getPrice() { return price; }
    public String getDiscount() { return discount; }
    public int getImageResource() { return imageResource; }
}