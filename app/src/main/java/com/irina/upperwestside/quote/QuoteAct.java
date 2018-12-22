package com.irina.upperwestside.quote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.irina.upperwestside.R;


public class QuoteAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        this.getRandomQuote(null);
    }

    public void getRandomQuote(View view){
        QuoteUpdateRunnable runnable = new QuoteUpdateRunnable(this);
        new Thread(runnable).start();
    }
}
