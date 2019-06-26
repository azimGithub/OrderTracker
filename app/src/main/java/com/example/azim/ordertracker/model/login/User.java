package com.example.azim.ordertracker.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("Co_id")
    @Expose
    public String coId;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("status")
    @Expose
    public Integer status;
}
