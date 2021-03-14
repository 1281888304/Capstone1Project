package com.example.logindatabase;

public class Producthelper {
    String liquidSupport;
    String teaSupport;
    String number;//field

    public void setLiquidSupport(String liquidSupport) {
        this.liquidSupport = liquidSupport;
    }

    public void setTeaSupport(String teaSupport) {
        this.teaSupport = teaSupport;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLiquidSupport() {
        return liquidSupport;
    }

    public String getTeaSupport() {
        return teaSupport;
    }

    public String getNumber() {
        return number;
    }

    public Producthelper(String liquidSupport, String teaSupport, String number) {
        this.liquidSupport = liquidSupport;
        this.teaSupport = teaSupport;
        this.number = number;
    }

    public Producthelper(){}



}
