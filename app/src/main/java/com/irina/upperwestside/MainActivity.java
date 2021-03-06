package com.irina.upperwestside;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.irina.upperwestside.boernerBar.BoernerBarAct;
import com.irina.upperwestside.fleaMarket.FleaMarketAct;
import com.irina.upperwestside.location.LocationsAct;
import com.irina.upperwestside.quote.QuoteAct;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar cToolbar = findViewById(R.id.c_toolbar);
        setSupportActionBar(cToolbar);

        addTouchListenerToAllButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.AboutMenEntry:
                Intent aboutIntent = new Intent(MainActivity.this, About.class);
                startActivity(aboutIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void addTouchListenerToAllButtons() {
        List<ImageButton> buttons = new ArrayList<>();
        buttons.add((ImageButton) findViewById(R.id.boernerBarBtn));
        buttons.add((ImageButton) findViewById(R.id.fleaMarketBtn));
        buttons.add((ImageButton) findViewById(R.id.hsUlmBtn));
        buttons.add((ImageButton) findViewById(R.id.randomQuoteBtn));

        for (ImageButton button : buttons) {
            button.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    ImageButton v1 = (ImageButton) v;
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v1.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                            v.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            v1.clearColorFilter();
                            v.invalidate();
                            break;
                        }
                    }
                    return false;
                }
            });
        }

    }

    public void openBoernerBarAct(View view) {
        Intent boernerBarIntent = new Intent(MainActivity.this, BoernerBarAct.class);
        startActivity(boernerBarIntent);
    }

    public void openLocationsAct(View view) {
        Intent locationsIntent = new Intent(MainActivity.this, LocationsAct.class);
        startActivity(locationsIntent);
    }

    public void openFleaMarketAct(View view) {
        Intent fleaMarketIntent = new Intent(MainActivity.this, FleaMarketAct.class);
        startActivity(fleaMarketIntent);
    }

    public void openRandomQuoteAct(View view) {
        Intent quoteIntent = new Intent(MainActivity.this, QuoteAct.class);
        startActivity(quoteIntent);
    }
}
