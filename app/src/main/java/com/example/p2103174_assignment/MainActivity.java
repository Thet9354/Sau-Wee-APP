package com.example.p2103174_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.p2103174_assignment.Fragment.AddVisit;
import com.example.p2103174_assignment.Fragment.Home;
import com.example.p2103174_assignment.Fragment.MapView;
import com.example.p2103174_assignment.Fragment.PastVisits;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    private RelativeLayout fragment_container;
    private ChipNavigationBar bottom_nav_bar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initWidget();
    }

    private void initWidget() {
        fragment_container = findViewById(R.id.fragment_container);
        bottom_nav_bar = findViewById(R.id.bottom_nav_bar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
        bottom_nav_bar.setItemSelected(R.id.bottom_nav_bar, true);
        bottomMenu();
    }

    private void bottomMenu() {
        bottom_nav_bar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch(i)
                {
                    case R.id.navigation_home:
                        fragment = new Home();
                        break;

                    case R.id.navigation_mapview:
                        fragment = new MapView();
                        break;

                    case R.id.navigation_addVisit:
                        fragment = new AddVisit();
                        break;

                    case R.id.navigation_pastVisit:
                        fragment = new PastVisits();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }
}