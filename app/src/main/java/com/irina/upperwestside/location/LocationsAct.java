package com.irina.upperwestside.location;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.irina.upperwestside.R;

public class LocationsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
    }

    public void showLocationInGMaps(View view) {
        String requestTerm = "Ulm";
        switch (view.getId()) {
            case R.id.hsUlmPrittwitzTxt:
            case R.id.hsUlmPrittwitzGMapsBtn:
            case R.id.hsUlmPrittwitzBtn:
                requestTerm = "Hs Ulm Prittwitzstraße";
                break;
            case R.id.hsUlmBoefingenTxt:
            case R.id.hsUlmBoefingenGMapsBtn:
            case R.id.hsUlmBoefingenBtn:
                requestTerm = "Hs Ulm Böfingen";
                break;
            case R.id.hsUlmEselsbergTxt:
            case R.id.hsUlmEselsbergGMapsBtn:
            case R.id.hsUlmEselsbergBtn:
                requestTerm = "Hs Ulm Oberer Eselsberg";
                break;
        }
        Intent countryIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0`?q=" + requestTerm));
        startActivity(countryIntent);
    }
}
