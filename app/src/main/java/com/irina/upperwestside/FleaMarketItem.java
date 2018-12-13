package com.irina.upperwestside;

public class FleaMarketItem {
    private String itemName;
    private double itemId;
    private String imageName;


    public FleaMarketItem(String itemName, String imageName, double itemPrice) {
        this.itemName = itemName;
        this.itemId = itemPrice;
        this.imageName = imageName;
    }

    public void setItemId(double itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getImageId() {
        return imageName;
    }

    public double getItemId() {
        return itemId;
    }
}
