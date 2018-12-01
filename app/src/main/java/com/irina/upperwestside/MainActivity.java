package com.irina.upperwestside;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar cToolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(cToolbar);

        addTouchListenerToAllButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private void addTouchListenerToAllButtons() {
        List<ImageButton> buttons = new ArrayList<>();
        buttons.add((ImageButton) findViewById(R.id.boernerBarBtn));
        buttons.add((ImageButton) findViewById(R.id.fleaMarketBtn));
        buttons.add((ImageButton) findViewById(R.id.hsUlmBtn));
        buttons.add((ImageButton) findViewById(R.id.volleyballBtn));
        buttons.add((ImageButton) findViewById(R.id.washingMachineBtn));

        for (ImageButton button : buttons) {
            button.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    ImageButton v1 = (ImageButton) v;
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            v1.setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY);
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
}
