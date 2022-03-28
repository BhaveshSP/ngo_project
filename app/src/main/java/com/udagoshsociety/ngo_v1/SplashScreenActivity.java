package com.udagoshsociety.ngo_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.udagoshsociety.ngo_v1.signInSignUp.RegistrationActivity;
import com.udagoshsociety.ngo_v1.signInSignUp.VerificationActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        goToMain();
    }
    private void goToMain(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        new Handler().postDelayed(() -> {
            Intent i ;
//            if(user == null){
//                 i = new Intent(SplashScreenActivity.this, VerificationActivity.class);
//            }else{
                 i = new Intent(SplashScreenActivity.this, MainActivity.class);
//            }
            startActivity(i);
            finish();
        },3000);
    }
}