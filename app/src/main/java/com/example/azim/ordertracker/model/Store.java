package com.example.azim.ordertracker.model;

import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("id")
    Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    String name;

    @SerializedName("image")
    String profileImage;

    @SerializedName("phone")
    String storeName;

    @SerializedName("email")
    String storeCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
}
