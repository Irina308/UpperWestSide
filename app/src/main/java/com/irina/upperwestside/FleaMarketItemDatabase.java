package com.irina.upperwestside;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.round;

class FleaMarketItemDatabase {

    private final static List<FleaMarketItem> FLEA_MARKET_ITEMS = new ArrayList<>();

    FleaMarketItemDbHelper dbHelper ;

    FleaMarketItemDatabase (Context context) {
        dbHelper = new FleaMarketItemDbHelper(context);
    }


    private void setFleaMarketItems(List<FleaMarketItem> list){
        FLEA_MARKET_ITEMS.clear();
        FLEA_MARKET_ITEMS.addAll(list);
        Collections.reverse(FLEA_MARKET_ITEMS);
    }

    List<FleaMarketItem> getFleaMarketItems() {
        return FLEA_MARKET_ITEMS;
    }

    void updateFleaMarketItemsFromDB(){
        List<FleaMarketItem> result = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String [] projection = { // Columns to include in result
                //    BaseColumns._ID,
                FleaMarketItemDbHelper.IMAGE_COL_IMAGE_ID,
                FleaMarketItemDbHelper.IMAGE_COL_DESCRITPION,
                FleaMarketItemDbHelper.IMAGE_COL_TITLE,
                FleaMarketItemDbHelper.IMAGE_COL_PRICE
        };


        // FILTER ROWS -> ich brauchs nicht, ich will einfach alle einträge
        String selection = FleaMarketItemDbHelper.IMAGE_TABLE + " = ?";
        String [] selectionArgs = {""};

        Cursor c = db.query(FleaMarketItemDbHelper.IMAGE_TABLE, projection, null, null, null, null, null);


        while (c.moveToNext()) {
            String title = c.getString(c.getColumnIndexOrThrow(FleaMarketItemDbHelper.IMAGE_COL_TITLE));
            String description = c.getString(c.getColumnIndexOrThrow(FleaMarketItemDbHelper.IMAGE_COL_DESCRITPION));
            String imageId = c.getString(c.getColumnIndexOrThrow(FleaMarketItemDbHelper.IMAGE_COL_IMAGE_ID));
            double price = c.getDouble(c.getColumnIndexOrThrow(FleaMarketItemDbHelper.IMAGE_COL_PRICE));

            result.add(new FleaMarketItem(title, description, imageId, price));

            Log.d("DatabaseTest", imageId);
        }
        c.close();

        this.setFleaMarketItems(result);
    }

    public void addToFleaMarketDb(String title, String imageId, String description, double price){
        // DATABASEE
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FleaMarketItemDbHelper.IMAGE_COL_IMAGE_ID, imageId);
        values.put(FleaMarketItemDbHelper.IMAGE_COL_DESCRITPION, description);
        values.put(FleaMarketItemDbHelper.IMAGE_COL_TITLE, title);
        values.put(FleaMarketItemDbHelper.IMAGE_COL_PRICE, price);

        //Insert data in table and return primary ID of new row
        long newRowId = db.insert(FleaMarketItemDbHelper.IMAGE_TABLE, null, values);

        // DATABASE END

        this.updateFleaMarketItemsFromDB();
    }
}
