package com.example.logindatabase.ui.powder;

public class Powders {
//    String toppingName;
//    String toppingImage;
//    String toppingPrice;
//    String toppingNum;
//    String toppingTitle;

    private String powderName;
    private String powderImage;
    private String powderPrice;
    private String powderNum;
    private String powderTitle;

    public Powders() {
    }

    public Powders(String powderName, String powderImage, String powderPrice, String powderNum, String powderTitle) {
        this.powderName = powderName;
        this.powderImage = powderImage;
        this.powderPrice = powderPrice;
        this.powderNum = powderNum;
        this.powderTitle = powderTitle;
    }

    public String getPowderName() {
        return powderName;
    }

    public void setPowderName(String powderName) {
        this.powderName = powderName;
    }

    public String getPowderImage() {
        return powderImage;
    }

    public void setPowderImage(String powderImage) {
        this.powderImage = powderImage;
    }

    public String getPowderPrice() {
        return powderPrice;
    }

    public void setPowderPrice(String powderPrice) {
        this.powderPrice = powderPrice;
    }

    public String getPowderNum() {
        return powderNum;
    }

    public void setPowderNum(String powderNum) {
        this.powderNum = powderNum;
    }

    public String getPowderTitle() {
        return powderTitle;
    }

    public void setPowderTitle(String powderTitle) {
        this.powderTitle = powderTitle;
    }
}
