package com.example.logindatabase;

public class Product {

    private String productName;
    private String productImage;
    private String productPrice;
    private String productNum;
    private String productTitle;

    public Product() {
    }

    public Product(String productName, String productImage,
                   String productPrice, String productNum, String productTitle) {
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productNum = productNum;
        this.productTitle = productTitle;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
