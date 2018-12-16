package com.irina.upperwestside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

public class FleaMarketItemDetailsAct extends AppCompatActivity {

    FleaMarketItemDatabase fleaMarketDb = new FleaMarketItemDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flea_market_item_details);
        EditText price_input = findViewById(R.id.item_price_input);
        price_input.setFilters(new InputFilter[] {new PriceDecimalFormatInputFilter()});

    }

    public void finishActivity(View view) {
        String imageId = (String) getIntent().getSerializableExtra("imageId");

        String itemTitleInput = ((EditText) findViewById(R.id.item_title_input)).getText().toString();
        double itemPriceInput = Double.valueOf(((EditText) findViewById(R.id.item_price_input)).getText().toString());
        String itemDescriptionInput = ((EditText) findViewById(R.id.item_description_input)).getText().toString();

        this.fleaMarketDb.addToFleaMarketDb(itemTitleInput, imageId, itemDescriptionInput, itemPriceInput);
        FleaMarketItemDetailsAct.this.finish();
    }
}
