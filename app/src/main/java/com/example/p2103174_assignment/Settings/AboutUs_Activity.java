package com.example.p2103174_assignment.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p2103174_assignment.MainActivity;
import com.example.p2103174_assignment.R;

public class AboutUs_Activity extends AppCompatActivity {

    private Button btn_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void initWidget() {

        btn_getStarted = findViewById(R.id.btn_getStarted);
    }
}