
package com.example.azim.ordertracker.model.appData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Salesman {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("Co_id")
    @Expose
    public String coId;
    @SerializedName("user_id")
    @Expose
    public String userId;

}
