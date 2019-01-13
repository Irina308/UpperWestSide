package com.irina.upperwestside.fleaMarket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.irina.upperwestside.Constants;
import com.irina.upperwestside.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

public class FleaMarketAdapter extends BaseAdapter {

    private List<FleaMarketItem> data;

    private FleaMarketAct fleaMarketAct;


    FleaMarketAdapter(List<FleaMarketItem> data, FleaMarketAct fleaMarketAct) {
        this.data = data;
        this.fleaMarketAct = fleaMarketAct;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        final FleaMarketItem entry = data.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Objects.requireNonNull(inflater).inflate(R.layout.flea_market_item, null, false); //R.layout.list_entry == meine neu erstellte list_entry.xml
        }

        ContextWrapper cw = new ContextWrapper(this.fleaMarketAct.getApplicationContext());
        String directory = cw.getDir("imageDir", Context.MODE_PRIVATE).getAbsolutePath();// path to /data/data/yourapp/app_data/imageDir

        ImageView fleaMarketItemImageView = convertView.findViewById(R.id.flea_market_item_img);
        setFleaMarketItemImageViewPicture(convertView, context, entry, directory, fleaMarketItemImageView);

        TextView fleaMarketItemDescriptionTxtView = convertView.findViewById(R.id.flea_market_item_description_txt);
        fleaMarketItemDescriptionTxtView.setText(String.valueOf(entry.getItemDescription()));

        TextView fleaMarketItemTitleTxtView = convertView.findViewById(R.id.flea_market_item_title);
        fleaMarketItemTitleTxtView.setText(String.valueOf(entry.getTitle()));

        TextView fleaMarketItemPriceTxtView = convertView.findViewById(R.id.flea_market_item_price_txt);
        fleaMarketItemPriceTxtView.setText(String.valueOf(new DecimalFormat("#.00").format(entry.getItemPrice())).concat(" EUR"));

        // Long push
        fleaMarketItemImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                fleaMarketAct.createDeletionDialog(entry.getImageId());
                return true;
            }
        });

        return convertView;
    }

    private void setFleaMarketItemImageViewPicture(View convertView, Context context, FleaMarketItem entry, String directory, ImageView fleaMarketItemImageView) {
        Bitmap bitmapImage = this.getBitmapImageFromFileSystem(directory, entry.getImageId());
        if (bitmapImage != null) {
            fleaMarketItemImageView.setImageBitmap(bitmapImage);
        } else {
            int defaultPicIdentifier = convertView.getResources().getIdentifier(Constants.DEFAULT_PIC_NAME, "drawable", context.getPackageName()); // "com.irina.inf3moad:drawable/" null null
            fleaMarketItemImageView.setImageResource(defaultPicIdentifier);
        }
    }


    private Bitmap getBitmapImageFromFileSystem(String imgPath, String imgName) {
        try {
            File f = new File(imgPath, imgName + ".jpg");
            return BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            Log.i("FleaMarketAdapter", "Picture not found. Use default picture for flea market item.");
            e.printStackTrace();
        }
        return null;
    }
}
