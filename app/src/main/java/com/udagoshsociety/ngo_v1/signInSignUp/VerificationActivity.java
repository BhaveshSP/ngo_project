package com.udagoshsociety.ngo_v1.signInSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.udagoshsociety.ngo_v1.MainActivity;
import com.udagoshsociety.ngo_v1.R;

import java.util.concurrent.TimeUnit;

public class VerificationActivity extends AppCompatActivity {
    private EditText phoneNumber,verifyCode;
    private Button verifyButton;
    private ProgressBar progressBar;
    private String verificationId = "";
    private boolean codeSent = false;
    private FirebaseAuth auth;
    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            Toast.makeText(VerificationActivity.this,
                    "Verification Complete", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerificationActivity.this,
                    "Verification Code Not Send\n Please Check your Internet", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "FirebaseException: "+e.getMessage());
            Log.d(TAG, "FirebaseException: "+e.getLocalizedMessage());
            progressBar.setVisibility(View.GONE);
            phoneNumber.setVisibility(View.VISIBLE);
            verifyButton.setText("Send OTP");
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText(VerificationActivity.this, "Verification Code Sent", Toast.LENGTH_SHORT).show();
            verifyCode.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            verifyButton.setText("Verify");
            verificationId = s;
            codeSent = true;

        }
    };

    private static final String TAG = "VerificationTAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verficiation);
        phoneNumber = findViewById(R.id.verifyPhoneNumberText);
        verifyButton = findViewById(R.id.verifyButton);
        progressBar = findViewById(R.id.verifyProgressBar);
        verifyCode = findViewById(R.id.verificationCodeText);
        auth = FirebaseAuth.getInstance();
        verifyButton.setOnClickListener(view -> {
            if (codeSent){
                if (verifyCode.getText().toString().equals("")){
                    Toast.makeText(this, "Please Enter Valid Verification Code", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    signInWithCred(verifyCode.getText().toString());
                }
            }else {
                if(phoneNumber.getText().toString().equals("")){
                    Log.d(TAG, "Number Empty: ");
                    Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                }else{
                    phoneNumber.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    sendVerificationCode("+91"+phoneNumber.getText().toString());
                }
            }
        });
    }

    private void sendVerificationCode(String phoneNumber){
        PhoneAuthOptions options =  PhoneAuthOptions.newBuilder(auth).setActivity(this)
                .setTimeout(4L, TimeUnit.SECONDS)
                .setPhoneNumber(phoneNumber)
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithCred(String verificationCode){
        PhoneAuthCredential cred = PhoneAuthProvider.getCredential(verificationId,verificationCode);
        auth.signInWithCredential(cred).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                goToMain();
            }else{
                Toast.makeText(this, "Verification Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}