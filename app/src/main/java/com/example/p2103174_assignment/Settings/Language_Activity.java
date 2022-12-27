package com.example.p2103174_assignment.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.p2103174_assignment.R;

public class Language_Activity extends AppCompatActivity {

    private ImageView btn_recommendations;

    private TextView txtView_Done, txtView_recommendation, txtView_recommendationText;

    private RelativeLayout rel_recommendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainSettings_Activity.class));
            }
        });

        rel_recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LanguageChoice_Activity.class));
            }
        });
    }

    private void initWidget() {
        txtView_Done = findViewById(R.id.txtView_Done);

        rel_recommendation = findViewById(R.id.rel_recommendation);
    }
}