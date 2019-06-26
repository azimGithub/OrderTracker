package com.example.azim.ordertracker.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostLogin {

    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("password")
    @Expose
    public String password;

    @SerializedName("token")
    @Expose
    public String token;
    public ErrorLogin errorLogin;


}
