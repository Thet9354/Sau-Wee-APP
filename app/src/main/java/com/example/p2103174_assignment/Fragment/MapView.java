package com.example.p2103174_assignment.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.p2103174_assignment.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MapView extends Fragment implements OnMapReadyCallback {

    private Context mContext;
    private GoogleMap mMap;

    private String country;

    private LatLng countryLatLng;

    @BindView(R.id.btnContinue)
    androidx.appcompat.widget.AppCompatButton btnContinue;

    @BindView(R.id.acWhereLocation)
    AutoCompleteTextView acWhereLocation;

    private static final String[] COUNTRIES = new String[]{
            "Austria", "Finland", "Netherlands", "Ireland", "Sweden", "Germany", "Denmark", "Switzerland", "Norway",
            "France", "Spain", "Canada", "Bulgaria", "Belgium", "Estonia", "United Kingdom", "Luxembourg", "New Zealand",
            "Italy", "Australia", "Latvia", "Cyprus", "Singapore", "Japan", "North Macedonia", "South Korea", "Moldova",
            "Slovakia", "Romania", "Portugal", "Poland", "Czech Republic", "Slovenia", "Costa Rica", "Chile", "Iceland",
            "Lithuania", "Georgia", "Hungary", "United States", "Russia", "Greece", "Bosnia and Herzegovina", "India",
            "Malaysia", "Armenia", "South Africa", "Mauritius", "Uruguay", "Albania", "Bermuda", "Vancouver", "Baja",
            "Antigua", "Montreal", "Belize", "Cartagena", "Bali", "Japan", "Bangkok", "Goa", "Geneva", "Milan", "Paris", "Ibiza"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_map_view, null, false);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        mContext = getActivity();

        ButterKnife.bind(this, view);

        updateUI();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();

                onMapReady(mMap);
            }
        });

        return view;
    }

    private void updateUI() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        acWhereLocation.setAdapter(adapter);

        country = acWhereLocation.getText().toString();

        switch (country)
        {
            case "Austria":
                countryLatLng = new LatLng(47.259659, 11.400375);
                break;

            case "Finland":
                countryLatLng = new LatLng(60.192059, 24.945831 );
                break;

            case "Netherlands":
                countryLatLng = new LatLng(46.77, 45.73);
                break;

            case "Ireland":
                countryLatLng = new LatLng(53.350140, -6.266155 );
                break;

            case "Sweden":
                countryLatLng = new LatLng(59.334591 , 18.063240 );
                break;

            case "Norway":
                countryLatLng = new LatLng(59.913269, 10.739111);
                break;

            case "France":
                countryLatLng = new LatLng(48.85341, 2.3488);
                break;

            case "Spain":
                countryLatLng = new LatLng(40.4165, -3.70256);
                break;

            case "Canada":
                countryLatLng = new LatLng(43.70011, -79.4163);
                break;

            case "Bulgaria":
                countryLatLng = new LatLng(41.721532, 26.320317 );
                break;

            case "Belgium":
                countryLatLng = new LatLng(50.5010789, 4.4764595);
                break;

            case "Estonia":
                countryLatLng = new LatLng(58.654500 , 25.037227 );
                break;

            case "United Kingdom":
                countryLatLng = new LatLng(51.509865 , -0.118092 );
                break;

            case "Luxembourg":
                countryLatLng = new LatLng(49.611622, 6.131935);
                break;

            case "New Zealand":
                countryLatLng = new LatLng(-40.459644 , 175.834921 );
                break;

            case "Italy":
                countryLatLng = new LatLng(41.89193, 12.51133);
                break;

            case "Australia":
                countryLatLng = new LatLng(-25.734968, 134.489563);
                break;

            case "Latvia":
                countryLatLng = new LatLng(57.425010 , 25.900680 );
                break;

            case "Cyprus":
                countryLatLng = new LatLng(35.095192, 33.203430);
                break;

            case "Singapore":
                countryLatLng = new LatLng(1.290270, 103.851959);
                break;

            case "Japan":
                countryLatLng = new LatLng(35.652832, 139.839478);
                break;

            case "North Macedonia":
                countryLatLng = new LatLng(41.99646, 21.43141);
                break;

            case "South Korea":
                countryLatLng = new LatLng(37.532600, 127.024612);
                break;

            case "Moldova":
                countryLatLng = new LatLng(47.003670, 28.907089);
                break;

            case "Slovakia":
                countryLatLng = new LatLng(48.547200 , 19.471360 );
                break;

            case "Romania":
                countryLatLng = new LatLng(44.439663, 26.096306);
                break;

            case "Portugal":
                countryLatLng = new LatLng(41.574886, -6.190217);
                break;

            case "Poland":
                countryLatLng = new LatLng(51.624980, 20.816150 );
                break;

            case "Czech Republic":
                countryLatLng = new LatLng(50.073658 , 14.418540 );
                break;

            case "Slovenia":
                countryLatLng = new LatLng(45.770000 , 14.420000);
                break;

            case "Costa Rica":
                countryLatLng = new LatLng(9.900396 , 84.099841 );
                break;

            case "Chile":
                countryLatLng = new LatLng(-33.406991 , -71.649671 );
                break;

            case "Iceland":
                countryLatLng = new LatLng(64.128288 , -21.827774 );
                break;

            case "Lithuania":
                countryLatLng = new LatLng(55.169437, 23.881275);
                break;

            case "Georgia":
                countryLatLng = new LatLng(32.165623, -82.900078);
                break;

            case "Hungary":
                countryLatLng = new LatLng(37.090240, -95.712891);
                break;

            case "United States":
                countryLatLng = new LatLng(61.524010, 105.318756);
                break;

            case "Russia":
                countryLatLng = new LatLng(61.524010, 105.318756);
                break;

            case "Greece":
                countryLatLng = new LatLng(40.260298 , 24.249458 );
                break;

            case "Bosnia and Herzegovina":
                countryLatLng = new LatLng(43.856430 , 18.413029 );
                break;

            case "India":
                countryLatLng = new LatLng(12.972442 , 77.580643 );
                break;

            case "Malaysia":
                countryLatLng = new LatLng(3.140853 , 101.693207 );
                break;

            case "Armenia":
                countryLatLng = new LatLng(39.76099  , 45.333102  );
                break;

            case "South Africa":
                countryLatLng = new LatLng(-28.4792625 , 24.6727135 );
                break;

            case "Mauritius":
                countryLatLng = new LatLng(-20.20665 , 57.6755 );
                break;

            case "Uruguay":
                countryLatLng = new LatLng(-32.522779 , -55.765835 );
                break;

            case "Albania":
                countryLatLng = new LatLng(41.635543  , 19.712892  );
                break;
        }

    }

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(countryLatLng).title("Marker in " + country));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(countryLatLng));

        //for styling the google map.
        try {
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(mContext, R.raw.style_json));
            if (!success) {
                // Handle map style load failure
            }
        } catch (Resources.NotFoundException e) {
            // Oops, looks like the map style resource couldn't be found!
        }

        Marker melbourne = mMap.addMarker(new MarkerOptions().position(countryLatLng)
                .icon(getMarkerIcon("#68EADD")));
    }

    // method definition

    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }

}