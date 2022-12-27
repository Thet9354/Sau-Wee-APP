package com.example.p2103174_assignment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    PackageManager getPackageManager;
    GoogleMap map;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMaps);

        client = LocationServices.getFusedLocationProviderClient(this);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        String userdate = getIntent().getStringExtra("userDate");
        Double usrlat =  Double.parseDouble(getIntent().getStringExtra("userLat"));
        Double usrlong = Double.parseDouble(getIntent().getStringExtra("userLong"));

        LatLng latLng = new LatLng(usrlat, usrlong);

        MarkerOptions options = new MarkerOptions().position(latLng).title(userdate);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        map.addMarker(options);

    }
}