package com.irina.upperwestside.quote;

import android.util.Log;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irina.upperwestside.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class QuoteUpdateRunnable implements Runnable {

    /*
RANDOM QUOTE API

//Einfacher Body
https://geek-jokes.sameerkumar.website/api

//größerer json value
https://api.chucknorris.io/jokes/random
 */

    private final static String queryString = "https://api.chucknorris.io/jokes/random";

    private QuoteAct quoteAct;

    private QuoteDto quoteDto;

    QuoteUpdateRunnable(QuoteAct quoteAct) {
        this.quoteAct = quoteAct;
    }

    @Override
    public void run() {
        Log.i("RandomQuoteUpdate", "Start runnable");
        synchronized (QuoteUpdateRunnable.this) {
            this.quoteDto = getRandomQuote();
        }

        quoteAct.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView randomTxtView = quoteAct.findViewById(R.id.random_quote_text);
                randomTxtView.setText(quoteDto.getValue());
            }
        });

        Log.i("RandomQuoteUpdate", "End of runnable class");

    }

    private QuoteDto getRandomQuote() {
        try {
            URL url = new URL(queryString);
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            QuoteDto quoteDto = mapper.readValue(inputStream, QuoteDto.class);
            this.quoteAct.setQuoteDto(quoteDto);
            return quoteDto;
        } catch (Exception e) {
            Log.e("RandomQuote", "Cant query random quoteDto API");
            e.printStackTrace();
        }
        return null;
    }

}
