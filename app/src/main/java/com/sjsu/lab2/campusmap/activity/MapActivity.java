package com.sjsu.lab2.campusmap.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.sjsu.lab2.campusmap.R;

public class MapActivity extends AppCompatActivity {

    CanvasMap map;
    LocationManager lm;
    Location location;
    LocationListener locationListener;
    double longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        map = new CanvasMap(this);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        map.setBackgroundColor(Color.WHITE);
        ScrollView sv =new ScrollView(getApplicationContext());
        HorizontalScrollView hv =new HorizontalScrollView(this);
        sv.addView(map);
        hv.addView(sv);
        setContentView(hv);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 1);
            }
            return;
        }
        location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                double[] values = new double[]{latitude, longitude};
                map.setValues(values);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, locationListener);
    }
}
