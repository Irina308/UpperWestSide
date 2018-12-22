package com.irina.upperwestside;

import android.util.Log;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class QuoteUpdateRunnable implements Runnable {

    private final static String queryString = "https://api.chucknorris.io/jokes/random";

    private QuoteAct quoteAct;

    private Quote quote;

    QuoteUpdateRunnable(QuoteAct quoteAct) {
        this.quoteAct = quoteAct;
    }

    @Override
    public void run() {
        Log.i("CurrencyUpdate", "Start runnable");
        synchronized (QuoteUpdateRunnable.this) {
            this.quote = getRandomQuote();
        }

        quoteAct.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView randomTxtView = quoteAct.findViewById(R.id.random_quote_text);
                randomTxtView.setText(quote.getValue());
            }
        });

        Log.i("CurrencyUpdate", "End of runnable class");

    }

    private Quote getRandomQuote() {
        try {
            URL url = new URL(queryString);
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(inputStream, Quote.class);
        } catch (Exception e) {
            Log.e("RandomQuote", "Cant query random quote API");
            e.printStackTrace();
        }
        return null;
    }

}
