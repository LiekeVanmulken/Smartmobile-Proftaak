package com.wouterv.quantifiedstudents.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wouterv.quantifiedstudents.R;

public class FHICTAPIRedirectActivity extends AppCompatActivity {

    String link = "https://identity.fhict.nl/connect/authorize?client_id=i311425-nyx&scope=fhict%20fhict_personal&response_type=token&redirect_uri=fhictnyx://nyxcallback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fhictapiredirect);
        Intent i =  new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }
}
