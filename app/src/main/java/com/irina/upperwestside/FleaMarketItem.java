package com.irina.upperwestside;

public class FleaMarketItem {
    private String itemTitle;
    private String itemDescription;
    private double itemPrice;
    private String imageId;


    FleaMarketItem(String itemTitle, String itemDescription, String imageName, double itemPrice) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.imageId = imageName;
    }

    String getItemDescription() {
        return itemDescription;
    }

    String getImageId() {
        return imageId;
    }

    String getTitle() {
        return itemTitle;
    }

    double getItemPrice() {
        return itemPrice;
    }
}
