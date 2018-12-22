package com.irina.upperwestside.boernerBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.irina.upperwestside.R;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

public class BarMenuAdapter extends BaseAdapter {

    private static String DEFAULT_PIC_NAME = "pic_not_avail";

    private List<BarMenuItem> data;

    BarMenuAdapter(List<BarMenuItem> data) {
        this.data = data;
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
        Context context = parent.getContext();
        BarMenuItem entry = data.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Objects.requireNonNull(inflater).inflate(R.layout.bar_menu_entry, null, false);
        }

        TextView barMenuItemNameTxt = convertView.findViewById(R.id.bar_menu_item_name_txt);
        barMenuItemNameTxt.setText(entry.getItemName());

        String menuItemDrawableName = "drink_" + entry.getItemName().toLowerCase();

        ImageView drinkPictureImageView = convertView.findViewById(R.id.drink_picture);

        int drinkDrawableIdentifier = convertView.getResources().getIdentifier(menuItemDrawableName, "drawable", context.getPackageName()); // "com.irina.inf3moad:drawable/" null null
        if (drinkDrawableIdentifier == 0) {
            drinkDrawableIdentifier = convertView.getResources().getIdentifier(DEFAULT_PIC_NAME, "drawable", context.getPackageName()); // "com.irina.inf3moad:drawable/" null null
        }

        drinkPictureImageView.setImageResource(drinkDrawableIdentifier);

        TextView menuItemPriceTxt = convertView.findViewById(R.id.menu_item_price_txt);
        menuItemPriceTxt.setText(String.valueOf( new DecimalFormat("#0.00").format(entry.getItemPrice())).concat(" EUR"));

        return convertView;
    }
}
