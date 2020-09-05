package com.flowz.gads2020praticetask.display;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.flowz.gads2020praticetask.R;

public class SplashScreenActivity extends AppCompatActivity {

    private final int LENGHT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main = new Intent(SplashScreenActivity.this, LeaderBoardActivity.class);
                startActivity(main);
                finish();
            }
        }, LENGHT);
    }
}
