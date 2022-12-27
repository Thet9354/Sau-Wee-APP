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

public class Ad_Preference_Activity extends AppCompatActivity {


    private TextView txtView_Done;

    private androidx.appcompat.widget.SwitchCompat ad_preference_switch;

    private String personalizedAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ad_preference);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        ad_preference_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    onResume();
                else
                    Toast.makeText(Ad_Preference_Activity.this, "Personalized ads has been switched off", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateData() {
        // Update data in SQlite
    }

    private void initWidget() {

        txtView_Done = findViewById(R.id.txtView_Done);

        ad_preference_switch = findViewById(R.id.ad_preference_switch);
    }
}