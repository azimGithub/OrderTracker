
package com.example.azim.ordertracker.model.appData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prefitemlist {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("acode")
    @Expose
    public String acode;
    @SerializedName("icode")
    @Expose
    public String icode;
    @SerializedName("Qty")
    @Expose
    public Integer qty;
    @SerializedName("no_bills")
    @Expose
    public Integer noBills;
    @SerializedName("last_sale_date")
    @Expose
    public String lastSaleDate;
    @SerializedName("Co_id")
    @Expose
    public String coId;
    @SerializedName("User_Id")
    @Expose
    public String userId;

}
