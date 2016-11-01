package com.sjsu.lab2.campusmap.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjsu.lab2.campusmap.R;
import com.sjsu.lab2.campusmap.model.BuildingDetail;
import com.sjsu.lab2.campusmap.utility.MapUtility;

public class DetailActivity extends AppCompatActivity {

    private String BuildingName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //fetch intent
        Intent intent = getIntent();
        //fetch intent variables
        String Destination = intent.getStringExtra("Destination");
        String Source = intent.getStringExtra("Source");
        String Distance = intent.getStringExtra("Distance");
        String Duration = intent.getStringExtra("Duration");
        String Name = intent.getStringExtra("Name");
        int buildingImg = intent.getIntExtra("Image", 0);
        BuildingName = Name;

        //get handler to resources
        final TextView distanceText = (TextView) findViewById(R.id.distanceText);
        final TextView durationText = (TextView) findViewById(R.id.durationText);
        final TextView addressText = (TextView) findViewById(R.id.addressText);
        final ImageView buildingImage  = (ImageView) findViewById(R.id.buildingImage);

        //update resources
        distanceText.setText(Distance);
        durationText.setText(Duration);
        addressText.setText(Destination);
        setTitle(Name);
        buildingImage.setImageResource(buildingImg);
    }

    public void onStreetViewButtonClick(View view) {

        BuildingDetail bd = MapUtility.seacrhFor(BuildingName);
        System.out.println("Latitude: "+bd.getLat());
        System.out.println("Longitude: "+bd.getLng());
        Intent streetView = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll="+ bd.getLat()+","+bd.getLng()+"&cbp=1,99.56,,1,-5.27&mz=21"));
        startActivity(streetView);

        //Street View API goes HERE
        //TODO : Pass the same source/destinantion to the API
    }
}
