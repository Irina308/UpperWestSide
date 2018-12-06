package com.irina.upperwestside;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BarMenuAdapter extends BaseAdapter {

    public static String DEFAULT_PIC_NAME = "pic_not_avail";

    private List<BarMenuItem> data;
    public BarMenuAdapter(List<BarMenuItem> data) {this.data = data;}



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
        BarMenuItem entry = data.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bar_menu_entry, null, false); //R.layout.list_entry == meine neu erstellte list_entry.xml
        }


        TextView barMenuItemNameTxt = convertView.findViewById(R.id.bar_menu_item_name_txt);
        barMenuItemNameTxt.setText(entry.getItemName());

        String menuItemDrawableName = "drink_"+ entry.getItemName().toLowerCase();

        ImageView flagIconImageView = convertView.findViewById(R.id.flag_ico);
        int identifier = convertView.getResources().getIdentifier(menuItemDrawableName, "drawable", context.getPackageName()); // "com.irina.inf3moad:drawable/" null null

        if (identifier == 0 ) {
            identifier = convertView.getResources().getIdentifier(DEFAULT_PIC_NAME, "drawable", context.getPackageName()); // "com.irina.inf3moad:drawable/" null null
        }

        flagIconImageView.setImageResource(identifier);

        TextView menuItemPriceTxt = convertView.findViewById(R.id.menu_item_price_txt);
        menuItemPriceTxt.setText(String.valueOf(entry.getItemPrice()));

        return convertView;
    }
}
