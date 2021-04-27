package com.example.logindatabase.ui.shoppingCart;

public class OrderProduct {

    private String productName;
    private String productTitle;
    private String productNum;
    private String productPrice;
    private String timeStamp;
    private String name;
    private String address;


    public OrderProduct() {
    }

    public OrderProduct(String productName, String productTitle, String productNum,
                        String productPrice, String timeStamp, String name, String address) {
        this.productName = productName;
        this.productTitle = productTitle;
        this.productNum = productNum;
        this.productPrice = productPrice;
        this.timeStamp = timeStamp;
        this.name = name;
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
