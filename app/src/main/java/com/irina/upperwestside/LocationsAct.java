package com.irina.upperwestside;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class LocationsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void showLocationInGMaps(View view) {
        String requestTerm = "Ulm";
        switch (view.getId()) {
            case R.id.hsUlmPrittwitzTxt:
            case R.id.hsUlmPrittwitzTimeBtn:
            case R.id.hsUlmPrittwitzBtn:
                requestTerm = "Hs Ulm Prittwitzstraße";
                break;
            case R.id.hsUlmBoefingenTxt:
            case R.id.hsUlmBoefingenTimeBtn:
            case R.id.hsUlmBoefingenBtn:
                requestTerm = "Hs Ulm Böfingen";
                break;
            case R.id.hsUlmEselsbergTxt:
            case R.id.hsUlmEselsbergTimeBtn:
            case R.id.hsUlmEselsbergBtn:
                requestTerm = "Hs Ulm Oberer Eselsberg";
                break;
        }
        Intent countryIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0`?q=" + requestTerm));
        startActivity(countryIntent);
    }
}
