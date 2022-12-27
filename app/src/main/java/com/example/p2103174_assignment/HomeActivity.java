package com.example.p2103174_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button aboutbtn = (Button) findViewById(R.id.Aboutbtn);
        Button addbtn = (Button) findViewById(R.id.Addbtn);
        Button historybtn = (Button) findViewById(R.id.historybtn);
        Button exitbtn = (Button) findViewById(R.id.exitbtn);

        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
               System.exit(0);
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 =new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent2);
            }
        });

        historybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 =new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(intent5);
            }
        });
    }
}