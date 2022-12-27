package com.example.p2103174_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class Starting_Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_splash);

        //for fullscrren
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //for animating logo
        StartAnimations();

        //idle screen time
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(getApplicationContext(), SplashScreen.class));
                    finish();
                }

            }
        };
        timerThread.start();
    }

    //animation for the splashlogo
    private void StartAnimations() {
        /*ImageView iv = (ImageView) findViewById(R.id.imgsplash);
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.bounce_in_down);
        anim.setTarget(iv);
        anim.setDuration(3000);
        anim.start();*/
        // Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_from_top);
        //iv.startAnimation(anim);
    }
}