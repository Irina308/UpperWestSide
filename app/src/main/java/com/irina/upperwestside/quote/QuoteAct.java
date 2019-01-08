package com.irina.upperwestside.quote;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.irina.upperwestside.R;


public class QuoteAct extends AppCompatActivity {

    private QuoteDto quoteDt;

    private int oldConfigOrientationValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        if ((oldConfigOrientationValue & ActivityInfo.CONFIG_ORIENTATION) == ActivityInfo.CONFIG_ORIENTATION) {  //  was rotated -> show old quote
            if (savedInstanceState != null) {
                quoteDt = (QuoteDto) savedInstanceState.getSerializable("RandomQuote");
                TextView randomTxtView = findViewById(R.id.random_quote_text);
                randomTxtView.setText(quoteDt.getValue());
            } else {
                this.showRandomQuote(null);
            }
        } else {   // was not rotated -> show new quote
            this.showRandomQuote(null);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("RandomQuote", quoteDt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        oldConfigOrientationValue = getChangingConfigurations();
    }

    public void showRandomQuote(View view) {
        QuoteUpdateRunnable runnable = new QuoteUpdateRunnable(this);
        new Thread(runnable).start();
    }

    public void setQuoteDto(QuoteDto quoteDto) {
        this.quoteDt = quoteDto;
    }
}
