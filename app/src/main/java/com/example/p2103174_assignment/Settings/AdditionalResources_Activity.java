package com.example.p2103174_assignment.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2103174_assignment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class AdditionalResources_Activity extends AppCompatActivity {

    private TextView txtView_Done;

    private RelativeLayout rel_termsOfService, rel_privacyPolicy, rel_cookiesUsed, rel_legalNotices;

    private androidx.appcompat.widget.SwitchCompat crashReport_switch;

    private String crashReport = "ON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_resources);

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

        crashReport_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    crashReport = "ON";
                else
                    crashReport = "OFF";
            }
        });

        rel_termsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                Toast.makeText(AdditionalResources_Activity.this, "This section is supposed to lead you to an external website which i have not developed, thanks for your understanding.", Toast.LENGTH_SHORT).show();
            }
        });

        rel_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                startActivity(new Intent(getApplicationContext(), PrivacyPolicy_Activity.class));
            }
        });

        rel_cookiesUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                Toast.makeText(AdditionalResources_Activity.this, "This section is supposed to lead you to an external website which i have not developed, thanks for your understanding.", Toast.LENGTH_SHORT).show();
            }
        });

        rel_legalNotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                startActivity(new Intent(getApplicationContext(), LegalNotice_Activity.class));
            }
        });
    }

    private void updateData() {

    }

    private void initWidget() {
        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);

        //--->RelativeLayout
        rel_termsOfService = findViewById(R.id.rel_termsOfService);
        rel_privacyPolicy = findViewById(R.id.rel_privacyPolicy);
        rel_cookiesUsed = findViewById(R.id.rel_cookiesUsed);
        rel_legalNotices = findViewById(R.id.rel_legalNotices);

        //--->Switch
        crashReport_switch = findViewById(R.id.crashReport_switch);
    }
}