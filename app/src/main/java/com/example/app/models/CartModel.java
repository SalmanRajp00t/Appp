package com.example.app.models;

public class CartModel {
    int image;
    String name;
    String orderNumber;
    String price;

   public CartModel(){

    }

    public CartModel(int image, String name, String orderNumber, String price) {
        this.image = image;
        this.name = name;
        this.orderNumber = orderNumber;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}