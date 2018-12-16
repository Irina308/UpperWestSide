package com.irina.upperwestside;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FleaMarketItemDatabase {

    private final static List<FleaMarketItem> FLEA_MARKET_ITEMS = new ArrayList<>();

    ImageDbHelper dbHelper ;

    FleaMarketItemDatabase (Context context) {
        dbHelper = new ImageDbHelper(context);
    }


    void setFleaMarketItems(List<FleaMarketItem> list){
        FLEA_MARKET_ITEMS.clear();
        FLEA_MARKET_ITEMS.addAll(list);
        Collections.reverse(FLEA_MARKET_ITEMS);
    }

    void addFleaMarketItem(FleaMarketItem item) {
        FLEA_MARKET_ITEMS.add(item);
    }

    List<FleaMarketItem> getFleaMarketItems() {
        return FLEA_MARKET_ITEMS;
    }



    public void updateFleaMarketItemsFromDB(){
        List<FleaMarketItem> result = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String [] projection = { // Columns to include in result
                //    BaseColumns._ID,
                ImageDbHelper.IMAGE_COL_ID,
                ImageDbHelper.IMAGE_COL_DESCRITPION
        };


        // FILTER ROWS -> ich brauchs nicht, ich will einfach alle einträge
        String selection = ImageDbHelper.IMAGE_TABLE + " = ?";
        String [] selectionArgs = {""};

        Cursor c = db.query(ImageDbHelper.IMAGE_TABLE, projection, null, null, null, null, null);


        while (c.moveToNext()) {
            String imageId = c.getString(c.getColumnIndexOrThrow(ImageDbHelper.IMAGE_COL_ID));
            String description = c.getString(c.getColumnIndexOrThrow(ImageDbHelper.IMAGE_COL_DESCRITPION));

            result.add(new FleaMarketItem(description, imageId, 20D));

            Log.d("DatabaseTest", imageId);
        }

        this.setFleaMarketItems(result);
    }

    public void addToFleaMarketDb(String imageId, String description){
        // DATABASEE
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ImageDbHelper.IMAGE_COL_ID, imageId);
        values.put(ImageDbHelper.IMAGE_COL_DESCRITPION, description);

        //Insert data in table and return primary ID of new row
        long newRowId = db.insert(ImageDbHelper.IMAGE_TABLE, null, values);

        // DATABASE END

        this.updateFleaMarketItemsFromDB();
    }
}
