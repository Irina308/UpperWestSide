package com.irina.upperwestside;

import android.database.sqlite.SQLiteDatabase;

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

    public void setFleaMarketItems(List<FleaMarketItem> list){
        FLEA_MARKET_ITEMS.clear();
        FLEA_MARKET_ITEMS.addAll(list);
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
