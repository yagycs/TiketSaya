package com.adeeva.tiketsaya;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        //setting timer untuk 1 detik
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // pindah activty lain
                Intent gogetStarted = new Intent(SplashActivity.this, GetStartedActivity.class);
                startActivity(gogetStarted);
                finish();
            }
        }, 1000);
    }
}
