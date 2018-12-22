package com.irina.upperwestside.boernerBar;

class BarMenuItem {
    private String itemName;

    private double itemPrice;

    BarMenuItem(String itemName, double itemPriceInEur) {
        this.itemName = itemName;
        this.itemPrice = itemPriceInEur;
    }

    String getItemName() {
        return itemName;
    }

    double getItemPrice() {
        return itemPrice;
    }
}
