package com.irina.upperwestside;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FleaMarketAct extends AppCompatActivity {

    FleaMarketItemDatabase fleaMarketDb = new FleaMarketItemDatabase();

    ImageDbHelper dbHelper = new ImageDbHelper(this);;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flea_market);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateFleaMarketItemsFromDB();// TODO an einem besseren ort machen

        initList();
    }

    private void initList() {
        ListView fleaMarketItemListView = findViewById(R.id.flea_market_item_list);

        // get all Images



//        FleaMarketAdapter myAdapter = new FleaMarketAdapter(fleaMarketDb.getFleaMarketItems(), this);
        // TODO was braucht der Adapter, damit er automatisch die liste aktualisiert, wie z.B. mit der statuischen LIste der elemente in einer klasse
        FleaMarketAdapter myAdapter = new FleaMarketAdapter(this.fleaMarketDb.getFleaMarketItems(), this);
        fleaMarketItemListView.setAdapter(myAdapter);
    }

    // REAL DATABASE

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

        fleaMarketDb.setFleaMarketItems(result);
    }


    public void onTakePhotoButtonClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 101); // 101 requestCode
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            // Open Intent for Details TODO
            String imageId = String.valueOf(System.currentTimeMillis()); // einfache ID generierung für diese Zwecke ausreichend, eigentlich sollte ein IDGenerator verwendet werden
            //FleaMarketItem fleaMarketItem = new FleaMarketItem("item name", imageId, 2D);


            // DATABASEE
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ImageDbHelper.IMAGE_COL_ID, imageId);
            values.put(ImageDbHelper.IMAGE_COL_DESCRITPION, "Some description");

            //Insert data in table and return primary ID of new row
            long newRowId = db.insert(ImageDbHelper.IMAGE_TABLE, null, values);

            // DATABASE END

            updateFleaMarketItemsFromDB();// TODO an einem besseren ort machen

          //  this.fleaMarketDb.addFleaMarketItem(fleaMarketItem);

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            saveToInternalStorage(bitmap, imageId);
        }
    }


    private void saveToInternalStorage(Bitmap bitmapImage, String imageId) {
        String imageName = imageId + ".jpg";

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        // View - Tool Windows - Device File explorer

        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File myPath = new File(directory, imageName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //   return directory.getAbsolutePath();
    }

}
