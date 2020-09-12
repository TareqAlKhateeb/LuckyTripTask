package com.example.luckytripassignment.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.luckytripassignment.R;
import com.example.luckytripassignment.ui.activities.host.HostActivity;

import java.util.Objects;

import static com.example.luckytripassignment.util.Constants.LongProgressBarDuration;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hides the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Hides the status bar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        setContentView(R.layout.activity_splash);

        //Timer to pause on splash screen
        new Handler().postDelayed(() -> {

            //Navigates to the next activity
            Intent i = new Intent(SplashActivity.this, HostActivity.class);
            startActivity(i);
            finish();

        }, LongProgressBarDuration);

    }
}