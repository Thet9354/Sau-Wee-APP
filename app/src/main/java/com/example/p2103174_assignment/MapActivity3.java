package com.example.p2103174_assignment;

import android.content.Context;
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

import java.util.ArrayList;

public class MapActivity3 extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    GoogleMap map;
    LocationManager locationManager;

    ArrayList<String> historyDate, historyLat, historyLong, historyFlyIn, historyFlyOut;
    ArrayList<MarkerOptions> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        historyDate = new ArrayList<>();
        historyLat = new ArrayList<>();
        historyLong = new ArrayList<>();
        historyFlyIn = new ArrayList<>();
        historyFlyOut = new ArrayList<>();

        options = new ArrayList<>(50);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMaps);

        client = LocationServices.getFusedLocationProviderClient(this);
        supportMapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        historyDate = getIntent().getStringArrayListExtra("userDateA");
        historyLat = getIntent().getStringArrayListExtra("userLatA");
        historyLong = getIntent().getStringArrayListExtra("userLongA");


        for(int i = 0; i< historyDate.size();i++){

            LatLng latLng = new LatLng(Double.parseDouble(historyLat.get(i)),Double.parseDouble(historyLong.get(i)));

            options.add(new MarkerOptions().position(latLng).title(historyDate.get(i)));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 0));
            map.addMarker(options.get(i));

        }

    }
}
