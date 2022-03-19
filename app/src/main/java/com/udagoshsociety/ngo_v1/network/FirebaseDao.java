package com.udagoshsociety.ngo_v1.network;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udagoshsociety.ngo_v1.models.User;

public class FirebaseDao {
    private static final String USER_DATA_BASE = "users";
    private final DatabaseReference reference = FirebaseDatabase.getInstance().getReference(USER_DATA_BASE);

    public Task<Void> createUser(String authId){
        return reference.child(authId).setValue(true);
    }

    public Task<Void> setUserData(User user,String authId){
        return reference.child(authId).setValue(user);
    }


}
