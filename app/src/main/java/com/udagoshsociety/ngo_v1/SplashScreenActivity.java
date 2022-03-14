package com.udagoshsociety.ngo_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.udagoshsociety.ngo_v1.signInsignUp.VerificationActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        goToMain();
    }
    private void goToMain(){
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreenActivity.this, VerificationActivity.class);
            startActivity(i);
            finish();
        },3000);
    }
}