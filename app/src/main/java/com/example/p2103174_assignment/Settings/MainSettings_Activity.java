package com.example.p2103174_assignment.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2103174_assignment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.onesignal.OneSignal;

import java.util.HashMap;

public class MainSettings_Activity extends AppCompatActivity {

    private TextView txtView_username, txtView_Done;

    private  de.hdodenhof.circleimageview.CircleImageView img_PFP;

    private ImageView btn_security, btn_textSize, btn_language, btn_feedback, btn_aboutUs, btn_additional;

    private RelativeLayout rel_security, rel_textSize, rel_language, rel_feedback, rel_aboutUs, rel_additional;

    private androidx.appcompat.widget.SwitchCompat nightMode_switch, notifications_Switch, privateAcc_switch;

    private Button btn_logOut;

    private static final String ONESIGNAL_APP_ID = "107c85b6-a95d-47a0-b62f-619df0861e13";

    private String mPhoneNumber, nightMode, notification, privateAcc, mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_settings);

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

        nightMode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    nightMode = "ON";
                }
                else
                {
                    nightMode = "OFF";
                }
            }
        });

        notifications_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    oneSignalSetup();
                    notification = "ON";
                }
                else
                    notification = "OFF";
            }
        });

        privateAcc_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    privateAcc = "ON";
                }
                else
                {
                    privateAcc = "OFF";
                }
            }
        });

        btn_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecurityNPrivacy_Activity.class);
                startActivity(intent);
            }
        });

        btn_textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TextSize_Activity.class);
                startActivity(intent);
            }
        });

        btn_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Language_Activity.class);
                startActivity(intent);
            }
        });

        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Feedback_Activity.class);
                startActivity(intent);
            }
        });

        btn_aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUs_Activity.class);
                startActivity(intent);
            }
        });

        btn_additional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdditionalResources_Activity.class);
                startActivity(intent);
            }
        });

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    private void updateData() {
        //TODO: Save the setting changes in a SQLite database
    }

    private void oneSignalSetup() {
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

    }

    private void initWidget() {
        txtView_username = findViewById(R.id.txtView_username);
        txtView_Done = findViewById(R.id.txtView_Done);

        img_PFP = findViewById(R.id.img_PFP);

        btn_security = findViewById(R.id.btn_security);
        btn_textSize = findViewById(R.id.btn_textSize);
        btn_language = findViewById(R.id.btn_language);
        btn_feedback = findViewById(R.id.btn_feedback);
        btn_aboutUs = findViewById(R.id.btn_aboutUs);
        btn_additional = findViewById(R.id.btn_additional);

        rel_security = findViewById(R.id.rel_security);
        rel_textSize = findViewById(R.id.rel_textSize);
        rel_language = findViewById(R.id.rel_language);
        rel_feedback = findViewById(R.id.rel_feedback);
        rel_aboutUs = findViewById(R.id.rel_aboutUs);
        rel_additional = findViewById(R.id.rel_additional);

        nightMode_switch = findViewById(R.id.nightMode_switch);
        notifications_Switch = findViewById(R.id.notifications_Switch);
        privateAcc_switch = findViewById(R.id.privateAcc_switch);

        btn_logOut = findViewById(R.id.btn_logOut);

    }
}