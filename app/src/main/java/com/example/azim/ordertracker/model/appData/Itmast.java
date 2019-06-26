
package com.example.azim.ordertracker.model.appData;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.azim.ordertracker.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.security.PublicKey;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Itmast implements Cloneable {

    List<Itmast> itemList;

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("icode")
    @Expose
    public String icode;
    @SerializedName("iname")
    @Expose
    public String iname;
    @SerializedName("sh_name")
    @Expose
    public Object shName;
    @SerializedName("sal_unit")
    @Expose
    public String salUnit;
    @SerializedName("sub_sal_unit")
    @Expose
    public String subSalUnit;
    @SerializedName("no_sl_unit")
    @Expose
    public Integer noSlUnit;
    @SerializedName("active")
    @Expose
    public Object active;
    @SerializedName("comm_per")
    @Expose
    public Object commPer;
    @SerializedName("cat")
    @Expose
    public Object cat;
    @SerializedName("co_code")
    @Expose
    public String coCode;
    @SerializedName("kgunit")
    @Expose
    public Integer kgunit;
    @SerializedName("op_bal")
    @Expose
    public Object opBal;
    @SerializedName("brand")
    @Expose
    public Object brand;
    @SerializedName("case_qty")
    @Expose
    public Integer caseQty;
    @SerializedName("hsn_code")
    @Expose
    public String hsnCode;
    @SerializedName("factor")
    @Expose
    public Integer factor;
    @SerializedName("gst_per")
    @Expose
    public String gstPer;
    @SerializedName("gst_cess_per")
    @Expose
    public String gstCessPer;
    @SerializedName("addl_gst_rate")
    @Expose
    public String addlGstRate;
    @SerializedName("comp_icode")
    @Expose
    public Object compIcode;
    @SerializedName("Sale_Rate")
    @Expose
    public String saleRate;
    @SerializedName("MRP")
    @Expose
    public Object mRP;
    @SerializedName("batch_no")
    @Expose
    public String batchNo;
    @SerializedName("Co_id")
    @Expose
    public String coId;
    @SerializedName("user_id")
    @Expose
    public String userId;

    public String quantity;
    public String price;
    public String schemeItem = "N";
    public String outletCount = "1";
    public int img;
    public String areaCd;
    public String storeCode;
    public String storeName;
    public String areaName;
    public boolean submit_flag = false;
    public boolean incompleteFlag = false;
    public String inv_no;
    public String inv_dt;
    public String sale_man;
    public String gross_amt;
    public String net_amt;
    public String srl_no;
    public String disc;
    public String disc_type;
    public String disc_amt;
    public String invlat;
    public String invlong;
    public String invaddress;

    @Override
    public Itmast clone()throws CloneNotSupportedException{
        return (Itmast)super.clone();
    }

}
