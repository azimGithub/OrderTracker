package com.example.azim.ordertracker.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorLogin {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("error")
    @Expose
    private String error;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}

