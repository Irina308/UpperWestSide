package com.irina.upperwestside;

import java.util.ArrayList;
import java.util.List;

class FleaMarketItemDatabase {

    private final static List<FleaMarketItem> FLEA_MARKET_ITEMS = new ArrayList<>();

    private static boolean HAS_BEEN_INITIALIZED = false;

    FleaMarketItemDatabase() {
        if (!HAS_BEEN_INITIALIZED) {
            initFleaMarket_Items();
        }
    }

    private void initFleaMarket_Items() {
        // Get all images from Folder TODO
        // Get zugehörige Details aus XML?
        HAS_BEEN_INITIALIZED = true;
    }

    void addFleaMarketItem(FleaMarketItem item) {
        FLEA_MARKET_ITEMS.add(item);
    }

    List<FleaMarketItem> getFleaMarketItems() {
        return FLEA_MARKET_ITEMS;
    }

}
