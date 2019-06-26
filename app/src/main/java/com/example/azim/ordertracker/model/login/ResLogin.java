package com.example.azim.ordertracker.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResLogin {

    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("user")
    @Expose
    public User user;

    public ErrorLogin errorLogin;
}
