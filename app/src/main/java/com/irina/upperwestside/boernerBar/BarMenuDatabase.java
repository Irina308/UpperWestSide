package com.irina.upperwestside.boernerBar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class BarMenuDatabase {

    private final static BarMenuItem[] MENU_ITEMS = {
            new BarMenuItem("Fanta", 1.0),
            new BarMenuItem("Cola", 1.0),
            new BarMenuItem("Sprite", 1.0),
            new BarMenuItem("Radler", 2.5),
            new BarMenuItem("Tee", 0.8),
            new BarMenuItem("Kaffee", 2.0),
            new BarMenuItem("Wasser", 0.5),
            new BarMenuItem("Apfelschorle", 1.0)
    };

    BarMenuItem[] getBarMenuItems() {
        return MENU_ITEMS;
    }

}