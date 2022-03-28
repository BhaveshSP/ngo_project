package com.udagoshsociety.ngo_v1.network;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udagoshsociety.ngo_v1.models.DonationRequest;
import com.udagoshsociety.ngo_v1.models.User;

public class FirebaseDao {
    private static final String USER_DATABASE = "users";
    private static final String DONATION_REQUEST_DATABASE = "donation_request";
    private final DatabaseReference user_database = FirebaseDatabase.getInstance().getReference(USER_DATABASE);
    private final DatabaseReference donation_request_database = FirebaseDatabase.getInstance().getReference(DONATION_REQUEST_DATABASE);

    public Task<Void> createUser(String authId){
        return user_database.child(authId).setValue(true);
    }

    public Task<Void> setUserData(User user,String authId){
        return user_database.child(authId).setValue(user);
    }

    public Task<Void> createRequestTableForUser(String authId){
        return donation_request_database.child(authId).setValue(true);
    }

    public Task<Void> setDonationRequestFromUser(String authId, DonationRequest request){
        return donation_request_database.child(authId).setValue(request);
    }

//    public  Task<Void> create


}
