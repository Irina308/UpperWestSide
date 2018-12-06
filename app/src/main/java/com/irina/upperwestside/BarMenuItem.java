package com.irina.upperwestside;

public class BarMenuItem {
    private String itemName;
    private double itemPrice;
    private String capital;


    public BarMenuItem(String itemName, String capital, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.capital = capital;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCapital() {
        return capital;
    }

    public double getItemPrice() {
        return itemPrice;
    }
}
