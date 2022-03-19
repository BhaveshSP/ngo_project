package com.udagoshsociety.ngo_v1.models;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User  {
    public String firstName,lastName,phoneNumber,gender;
    public String age;
    public User(String firstName,String lastName,String gender,String age,String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }
}
