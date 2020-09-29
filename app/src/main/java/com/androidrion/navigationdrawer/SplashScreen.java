package com.androidrion.navigationdrawer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashScreen extends Activity {
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView teks, teks2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.textView5);
        teks = findViewById(R.id.textView2);
        teks2 = findViewById(R.id.textView4);

        teks.setAnimation(topAnim);
        teks2.setAnimation(topAnim);
        image.setAnimation(bottomAnim);
    }

    @Override
    protected void onPause() { // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}