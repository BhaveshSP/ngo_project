package com.udagoshsociety.ngo_v1.signInSignUp;

import static com.udagoshsociety.ngo_v1.Constants.KEY_PHONE_NUMBER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.udagoshsociety.ngo_v1.Constants;
import com.udagoshsociety.ngo_v1.MainActivity;
import com.udagoshsociety.ngo_v1.R;
import com.udagoshsociety.ngo_v1.network.FirebaseDao;

import java.util.concurrent.TimeUnit;

public class VerificationActivity extends AppCompatActivity {
    private EditText phoneNumber,verifyCode;
    private Button verifyButton;
    private ProgressBar progressBar;
    private String verificationId = "";;
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
            phoneNumber.setText("Verification Code");
            verifyButton.setText("Verify");
            verificationId = s;
            codeSent = true;

        }
    };

    private static final String TAG = "TESTING";
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
                    signInWithCred(verifyCode.getText().toString(),this);
                }
            }else {
                if(phoneNumber.getText().toString().equals("")){
                    Log.d(TAG, "Number Empty: ");
                    Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                }else{
                    phoneNumber.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    String phoneNumberText = "+91"+phoneNumber.getText().toString();
                    SharedPreferences.Editor editor = getSharedPreferences(Constants.SHARED_PREF_KEY,MODE_PRIVATE).edit();
                    editor.putString(KEY_PHONE_NUMBER,phoneNumberText);
                    editor.apply();
//                    goToMain();
                    sendVerificationCode(phoneNumberText);
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

    private void signInWithCred(String verificationCode,Context context){
        PhoneAuthCredential cred = PhoneAuthProvider.getCredential(verificationId,verificationCode);
        auth.signInWithCredential(cred).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                goToMain(context);
            }else{
                Toast.makeText(this, "Verification Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToMain(Context context){
        FirebaseDao dao = new FirebaseDao();
        String authId = FirebaseAuth.getInstance().getUid();
        dao.createUser(authId).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(context, RegistrationActivity.class);
                    startActivity(intent);
                }

            }
        });
    }



}