package com.udagoshsociety.ngo_v1.models;

public class DonationOption {
    public int imageId = 0;
    public String title = "";

    public DonationOption(int imageId,String title){
        this.imageId = imageId;
        this.title = title;
    }


    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
