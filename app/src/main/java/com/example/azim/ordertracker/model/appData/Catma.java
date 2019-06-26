
package com.example.azim.ordertracker.model.appData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Catma {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("cat_code")
    @Expose
    public String catCode;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("Co_id")
    @Expose
    public String coId;
    @SerializedName("user_id")
    @Expose
    public String userId;

}
