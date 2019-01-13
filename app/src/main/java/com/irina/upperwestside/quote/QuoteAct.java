package com.irina.upperwestside.quote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.irina.upperwestside.R;


public class QuoteAct extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) { // Wont be called on orientation change due to manifest entry (android:configChanges="orientation").
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        this.showRandomQuote(null);
    }


    public void showRandomQuote(View view) {
        QuoteUpdateRunnable runnable = new QuoteUpdateRunnable(this);
        new Thread(runnable).start();
    }

}
