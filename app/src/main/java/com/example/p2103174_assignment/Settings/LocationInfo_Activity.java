package com.example.p2103174_assignment.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2103174_assignment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class LocationInfo_Activity extends AppCompatActivity {

    private TextView txtView_Done;

    private androidx.appcompat.widget.SwitchCompat locationInformation_switch, preciseLocation_switch;

    private String personalized = "ON";

    private String preciseLocation = "OFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_location_info);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        locationInformation_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    personalized = "ON";
                else
                    personalized = "OFF";
            }
        });

        preciseLocation_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    preciseLocation = "ON";
                else
                    preciseLocation = "OFF";
            }
        });
    }

    private void updateData() {

    }

    private void initWidget() {
        txtView_Done = findViewById(R.id.txtView_Done);

        locationInformation_switch = findViewById(R.id.locationInformation_switch);
        preciseLocation_switch = findViewById(R.id.preciseLocation_switch);
    }
}