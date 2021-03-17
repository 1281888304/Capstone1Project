package com.example.logindatabase;

public class OrderShow {
    String address;
    String company;
    String item;
    String order_num;
    String quantity;
    String status;

    public OrderShow(){}

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public String getCompany() {
        return company;
    }

    public String getItem() {
        return item;
    }

    public String getOrder_num() {
        return order_num;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public  OrderShow(String address, String company, String item, String order_num, String quantity, String status){

    }
}
