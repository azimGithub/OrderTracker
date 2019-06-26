
package com.example.azim.ordertracker.model.appData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Areama {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("area_cd")
    @Expose
    public String areaCd;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("freq")
    @Expose
    public Object freq;
    @SerializedName("Co_id")
    @Expose
    public String coId;
    @SerializedName("user_id")
    @Expose
    public String userId;

}
