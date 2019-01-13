package com.irina.upperwestside.fleaMarket;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class FleaMarketItemDatabase {

    private final static List<FleaMarketItem> FLEA_MARKET_ITEMS = new ArrayList<>();

    private FleaMarketItemDbHelper dbHelper;

    private Context context;

    FleaMarketItemDatabase(Context context) {
        this.context = context;
        dbHelper = new FleaMarketItemDbHelper(context);
    }


    private void setFleaMarketItems(List<FleaMarketItem> list) {
        FLEA_MARKET_ITEMS.clear();
        FLEA_MARKET_ITEMS.addAll(list);
        Collections.reverse(FLEA_MARKET_ITEMS);
    }

    List<FleaMarketItem> getFleaMarketItems() {
        return FLEA_MARKET_ITEMS;
    }

    void updateFleaMarketItemsFromDB() {
        List<FleaMarketItem> result = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = { // Columns to include in result
                FleaMarketItemDbHelper.IMAGE_COL_IMAGE_ID,
                FleaMarketItemDbHelper.IMAGE_COL_DESCRITPION,
                FleaMarketItemDbHelper.IMAGE_COL_TITLE,
                FleaMarketItemDbHelper.IMAGE_COL_PRICE
        };

        Cursor c = db.query(FleaMarketItemDbHelper.IMAGE_TABLE, projection, null, null, null, null, null);

        while (c.moveToNext()) {
            String title = c.getString(c.getColumnIndexOrThrow(FleaMarketItemDbHelper.IMAGE_COL_TITLE));
            String description = c.getString(c.getColumnIndexOrThrow(FleaMarketItemDbHelper.IMAGE_COL_DESCRITPION));
            String imageId = c.getString(c.getColumnIndexOrThrow(FleaMarketItemDbHelper.IMAGE_COL_IMAGE_ID));
            double price = c.getDouble(c.getColumnIndexOrThrow(FleaMarketItemDbHelper.IMAGE_COL_PRICE));

            result.add(new FleaMarketItem(title, description, imageId, price));

            Log.d("ImageDB", c.toString());
        }
        c.close();

        this.setFleaMarketItems(result);
    }

    void addToFleaMarketDb(String title, String imageId, String description, double price) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FleaMarketItemDbHelper.IMAGE_COL_IMAGE_ID, imageId);
        values.put(FleaMarketItemDbHelper.IMAGE_COL_DESCRITPION, description);
        values.put(FleaMarketItemDbHelper.IMAGE_COL_TITLE, title);
        values.put(FleaMarketItemDbHelper.IMAGE_COL_PRICE, price);

        db.insert(FleaMarketItemDbHelper.IMAGE_TABLE, null, values);
        this.updateFleaMarketItemsFromDB();
    }

    void deleteItemAndImageFromFleaMarketDb(String imageId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FleaMarketItemDbHelper.IMAGE_COL_IMAGE_ID, imageId);

        int deleteReturnCode = db.delete(FleaMarketItemDbHelper.IMAGE_TABLE, FleaMarketItemDbHelper.IMAGE_COL_IMAGE_ID + "=?", new String[]{imageId});
        Toast toast = Toast.makeText(context, deleteReturnCode > 0 ? "Deleted item" : "Couldnt delete item!", Toast.LENGTH_LONG);
        toast.show();

        // Delete picture
        deletePictureFromInternalStorage(imageId);
        this.updateFleaMarketItemsFromDB();

    }

    private void deletePictureFromInternalStorage(String imageId) {
        String imageName = imageId + ".jpg";
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());// path to /data/data/yourapp/app_data/imageDir

        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File myPath = new File(directory, imageName);
        boolean deleteResult = myPath.delete();
        if(!deleteResult) {
            Log.e("FleaMarketItemDB", "Failed to delete image with id: " + imageId);
        }
    }
}
