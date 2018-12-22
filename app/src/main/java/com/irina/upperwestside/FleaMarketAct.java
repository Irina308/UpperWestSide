package com.irina.upperwestside;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class FleaMarketAct extends AppCompatActivity {

    FleaMarketItemDatabase fleaMarketDb = new FleaMarketItemDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flea_market);

        fleaMarketDb.updateFleaMarketItemsFromDB();// TODO an einem besseren ort machen

        initList();
    }

    private void initList() {
        ListView fleaMarketItemListView = findViewById(R.id.flea_market_item_list);

        // TODO was braucht der Adapter, damit er automatisch die liste aktualisiert, wie z.B. mit der statuischen LIste der elemente in einer klasse
        FleaMarketAdapter myAdapter = new FleaMarketAdapter(this.fleaMarketDb.getFleaMarketItems(), this);
        fleaMarketItemListView.setAdapter(myAdapter);
    }


//    public void onTakePhotoButtonClick(View view) {
//        // TODO please take picture in landscape mode
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, 101); // 101 requestCode
//    }

    public void onTakePhotoButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Please take the picture in landscape mode.")
                .setTitle("Hint")

                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 101); // 101 requestCode
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

           // einfache ID generierung für diese Zwecke ausreichend, eigentlich sollte ein IDGenerator verwendet werden
            String imageId = String.valueOf(System.currentTimeMillis());


            Intent fleaMarketItemDetailsIntent = new Intent(FleaMarketAct.this, FleaMarketItemDetailsAct.class);
            fleaMarketItemDetailsIntent.putExtra("imageId", imageId);
            startActivity(fleaMarketItemDetailsIntent);

//            this.fleaMarketDb.addToFleaMarketDb(imageId, "some description");

            Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
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

    public void createDeletionDialog(final String imageId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fleaMarketDb.deleteItemAndImageFromFleaMarketDb(imageId);

                        FleaMarketAdapter fleaMarketItemListAdapter = (FleaMarketAdapter) (((ListView) (findViewById(R.id.flea_market_item_list))).getAdapter());
                        fleaMarketItemListAdapter.notifyDataSetChanged();

                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();
    }


}
