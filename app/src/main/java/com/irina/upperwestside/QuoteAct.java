package com.irina.upperwestside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class QuoteAct extends AppCompatActivity {

    private final static String queryString = "https://api.chucknorris.io/jokes/random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        this.getRandomQuote();
    }

    private void getRandomQuote(){
        this.getRandomQuote(null);
    }

    public void getRandomQuote(View view){
        try {
            URL url = new URL(queryString);
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            Quote q = mapper.readValue(inputStream, Quote.class);

            TextView randomTxtView = findViewById(R.id.random_quote_text);
            randomTxtView.setText(q.getValue());



        } catch (Exception e) {
            Log.e("RandomQuote", "Cant query db");
            e.printStackTrace();
        }
    }
}
