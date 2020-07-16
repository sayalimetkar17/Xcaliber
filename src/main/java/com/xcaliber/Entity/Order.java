package com.xcaliber.Entity;

public class Order {
    private int customerid;
    private String item;
    private int quantity;
    private double price;

    public Order() {
    }

    public Order(int customerid, String item, int quantity, double price) {
        this.customerid = customerid;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
