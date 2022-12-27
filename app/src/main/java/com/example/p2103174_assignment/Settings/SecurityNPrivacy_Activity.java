package com.example.p2103174_assignment.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2103174_assignment.R;

public class SecurityNPrivacy_Activity extends AppCompatActivity {

    private TextView txtView_Done;

    private RelativeLayout rel_ad_preferences, rel_dataSharing, rel_locationInfo, rel_privacy, rel_privacyPolicy, rel_contactus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_security_nprivacy);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SecurityNPrivacy_Activity.class));
            }
        });

        rel_ad_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ad_Preference_Activity.class);
                startActivity(intent);
            }
        });

        rel_dataSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataSharing_Activity.class);
                startActivity(intent);
            }
        });

        rel_locationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocationInfo_Activity.class);
                startActivity(intent);
            }
        });

        rel_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecurityNPrivacy_Activity.this, "This section is supposed to lead you to an external website which i haven't create yet", Toast.LENGTH_SHORT).show();
            }
        });

        rel_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecurityNPrivacy_Activity.this, "To be continued", Toast.LENGTH_SHORT).show();
            }
        });

        rel_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecurityNPrivacy_Activity.this, "To be continued", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initWidget() {
        txtView_Done = findViewById(R.id.txtView_Done);

        rel_ad_preferences = findViewById(R.id.rel_ad_preferences);
        rel_dataSharing = findViewById(R.id.rel_dataSharing);
        rel_locationInfo = findViewById(R.id.rel_locationInfo);
        rel_privacy = findViewById(R.id.rel_privacy);
        rel_privacyPolicy = findViewById(R.id.rel_privacyPolicy);
        rel_contactus = findViewById(R.id.rel_contactus);
    }
}