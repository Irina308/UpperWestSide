package com.irina.upperwestside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



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
