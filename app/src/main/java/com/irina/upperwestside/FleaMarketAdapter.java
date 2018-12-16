package com.irina.upperwestside;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.List;

public class FleaMarketAdapter extends BaseAdapter {

    public static String DEFAULT_PIC_NAME = "pic_not_avail";


    private List<FleaMarketItem> data;

    private Context context;


    public FleaMarketAdapter(List<FleaMarketItem> data, Context context) {
        this.data = data;
        this.context = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        FleaMarketItem entry = data.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.flea_market_item, null, false); //R.layout.list_entry == meine neu erstellte list_entry.xml
        }


        ContextWrapper cw = new ContextWrapper(this.context.getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        // View - Tool Windows - Device File explorer

        String directory = cw.getDir("imageDir", Context.MODE_PRIVATE).getAbsolutePath();

        ImageView flagIconImageView = convertView.findViewById(R.id.flea_market_item_img);

        Bitmap bitmapImage = this.getBitmapImage(directory, entry.getImageId());
        if (bitmapImage != null) {
            flagIconImageView.setImageBitmap(bitmapImage);
        } else {
            int defaultPicIdentifier = convertView.getResources().getIdentifier(DEFAULT_PIC_NAME, "drawable", context.getPackageName()); // "com.irina.inf3moad:drawable/" null null
            flagIconImageView.setImageResource(defaultPicIdentifier);
        }

        TextView fleaMarketItemDescriptionTxtView = convertView.findViewById(R.id.flea_market_item_description_txt);
        fleaMarketItemDescriptionTxtView.setText(String.valueOf(entry.getItemDescription()));

        TextView fleaMarketItemTitleTxtView = convertView.findViewById(R.id.flea_market_item_title);
        fleaMarketItemTitleTxtView.setText(String.valueOf(entry.getTitle()));

        TextView fleaMarketItemPriceTxtView = convertView.findViewById(R.id.flea_market_item_price_txt);
        fleaMarketItemPriceTxtView.setText(String.valueOf(new DecimalFormat("#.00").format(entry.getItemPrice())).concat(" EUR"));


        return convertView;
    }

    private Bitmap getBitmapImage(String imgPath, String imgName) {
        try {
            File f = new File(imgPath, imgName + ".jpg");
            return BitmapFactory.decodeStream(new FileInputStream(f));
//            ImageView img=(ImageView)findViewById(R.id.image);
//            img.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null; // TODO default pic
    }
}
