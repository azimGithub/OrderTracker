package com.example.azim.ordertracker.model.postData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostData {
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
    public String areaCd;
    public String acode;
    public String aname;
}
