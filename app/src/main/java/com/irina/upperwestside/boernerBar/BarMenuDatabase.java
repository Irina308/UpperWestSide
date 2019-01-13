package com.irina.upperwestside.boernerBar;

class BarMenuDatabase {

    private final static BarMenuItem[] MENU_ITEMS = {
            new BarMenuItem("Fanta", 1.0),
            new BarMenuItem("Cola", 1.0),
            new BarMenuItem("Sprite", 1.0),
            new BarMenuItem("Radler", 2.5),
            new BarMenuItem("Tea", 0.8),
            new BarMenuItem("Coffee", 2.0),
            new BarMenuItem("Water", 0.5)};

    BarMenuItem[] getBarMenuItems() {
        return MENU_ITEMS;
    }

}