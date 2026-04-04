package com.example.kissanconnect;

public class Order_for_consumer {
    private String date, status, productName, farmerName, quantity, price;
    private int productImage;
    private boolean isLongTerm;

    public Order_for_consumer(String date, String status, int productImage, String productName,
                 String farmerName, String quantity, String price, boolean isLongTerm) {
        this.date = date;
        this.status = status;
        this.productImage = productImage;
        this.productName = productName;
        this.farmerName = farmerName;
        this.quantity = quantity;
        this.price = price;
        this.isLongTerm = isLongTerm;
    }

    // Getters
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public int getProductImage() { return productImage; }
    public String getProductName() { return productName; }
    public String getFarmerName() { return farmerName; }
    public String getQuantity() { return quantity; }
    public String getPrice() { return price; }
    public boolean isLongTerm() { return isLongTerm; }
}
