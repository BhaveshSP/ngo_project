package com.udagoshsociety.ngo_v1.models;

public class DonationRequest {
    String firstName;
    String lastName;
    String phoneNumber;
    String age;
    String donationType;
    public DonationRequest(String firstName,String lastName,String phoneNumber,String age , String donationType){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.donationType = donationType;
    }
}
