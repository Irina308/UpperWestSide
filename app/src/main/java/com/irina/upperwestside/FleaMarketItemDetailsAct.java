package com.irina.upperwestside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FleaMarketItemDetailsAct extends AppCompatActivity {

    FleaMarketItemDatabase fleaMarketDb = new FleaMarketItemDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flea_market_item_details);

        EditText price_input = findViewById(R.id.item_price_input);
        price_input.setFilters(new InputFilter[]{new PriceDecimalFormatInputFilter()});


    }

    public void finishActivity(View view) {
        String imageId = (String) getIntent().getSerializableExtra("imageId");

        EditText itemTitleInpEditText = findViewById(R.id.item_title_input);
        EditText itemPriceInput = findViewById(R.id.item_price_input);
        EditText itemDescriptionInput = findViewById(R.id.item_description_input);

        List<EditText> notFilledRequiredFields =new ArrayList<>();
        List<EditText> requiredFields = Arrays.asList(itemTitleInpEditText, itemPriceInput, itemDescriptionInput);
        for (EditText editText : requiredFields) {
            if("".equals(editText.getText().toString().trim())){
                notFilledRequiredFields.add(editText);
            }
        }

        if (!notFilledRequiredFields.isEmpty()) {
            for (EditText requField : notFilledRequiredFields) {
                requField.setError("Required!");
            }
        } else {
            this.fleaMarketDb.addToFleaMarketDb(
                    itemTitleInpEditText.getText().toString(),
                    imageId,
                    itemDescriptionInput.getText().toString(),
                    Double.valueOf(itemPriceInput.getText().toString()));
            FleaMarketItemDetailsAct.this.finish();
        }
    }
}

