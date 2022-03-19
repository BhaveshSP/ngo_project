package com.udagoshsociety.ngo_v1.signInSignUp;

import static com.udagoshsociety.ngo_v1.Constants.KEY_PHONE_NUMBER;
import static com.udagoshsociety.ngo_v1.Constants.SHARED_PREF_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.udagoshsociety.ngo_v1.MainActivity;
import com.udagoshsociety.ngo_v1.R;
import com.udagoshsociety.ngo_v1.models.User;
import com.udagoshsociety.ngo_v1.network.FirebaseDao;

public class RegistrationActivity extends AppCompatActivity {
    private EditText firstName,lastName,age;
    private static final String TAG = "TAGRegistrationActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstName = findViewById(R.id.registrationFirstNameTextInput);
        lastName = findViewById(R.id.registrationLastNameTextInput);
        age = findViewById(R.id.registrationAgeTextInput);
        Button doneButton = findViewById(R.id.registrationDoneButton);
        RadioGroup genderRadioGroup = findViewById(R.id.registrationGenderLayout);
        doneButton.setOnClickListener(view -> {
            if (firstName.getText().toString().equals("")){
                Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (lastName.getText().toString().equals("")){
                Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (age.getText().toString().equals("")){
                Toast.makeText(this, "Enter Age Name", Toast.LENGTH_SHORT).show();
                return;
            }
            final String[] gender = {"Male"};
            genderRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
                if (i == R.id.registrationMaleOption){
                    gender[0] = "Male";
                }else{
                    gender[0] = "Female";
                }
            });

            addUserToDataBase(firstName.getText().toString(),
                    lastName.getText().toString(),gender[0],
                    age.getText().toString());

        });
    }

    private void addUserToDataBase(String firstName,String lastName,String gender,String age){
        String phoneNumber = getSharedPreferences(SHARED_PREF_KEY,MODE_PRIVATE).getString(KEY_PHONE_NUMBER,"");
        User user = new User(firstName,lastName,gender,age,phoneNumber);
        FirebaseDao dao = new FirebaseDao();
        String authId = FirebaseAuth.getInstance().getUid();
        Log.d(TAG, "Iser: "+user.gender);
        Log.d(TAG, "Iser: "+user.firstName);
        Log.d(TAG, "Iser: "+user.lastName);
        Log.d(TAG, "Iser: "+user.phoneNumber);
        Log.d(TAG, "Iser: "+user.age);

        dao.setUserData(user,authId).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(this, "User Data Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Registration Error");
            }
        });
    }


}