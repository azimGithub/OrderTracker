package com.example.azim.ordertracker.model.postData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Orders {

        @SerializedName("inv_no")
        @Expose
        private String invNo;
        @SerializedName("inv_dt")
        @Expose
        private String invDt;
        @SerializedName("area_cd")
        @Expose
        private String areaCd;
        @SerializedName("acode")
        @Expose
        private String acode;
        @SerializedName("sale_man")
        @Expose
        private String saleMan;
        @SerializedName("gross_amt")
        @Expose
        private String grossAmt;
        @SerializedName("net_amt")
        @Expose
        private String netAmt;
        @SerializedName("invlat")
        @Expose
        private String invlat;
        @SerializedName("invlong")
        @Expose
        private String invlong;
        @SerializedName("invaddress")
        @Expose
        private String invaddress;
        @SerializedName("sblvars")
        @Expose
        private List<Sblvars> sblvars = null;

        public String getInvNo() {
            return invNo;
        }

        public void setInvNo(String invNo) {
            this.invNo = invNo;
        }

        public String getInvDt() {
            return invDt;
        }

        public void setInvDt(String invDt) {
            this.invDt = invDt;
        }

        public String getAreaCd() {
            return areaCd;
        }

        public void setAreaCd(String areaCd) {
            this.areaCd = areaCd;
        }

        public String getAcode() {
            return acode;
        }

        public void setAcode(String acode) {
            this.acode = acode;
        }

        public String getSaleMan() {
            return saleMan;
        }

        public void setSaleMan(String saleMan) {
            this.saleMan = saleMan;
        }

        public String getGrossAmt() {
            return grossAmt;
        }

        public void setGrossAmt(String grossAmt) {
            this.grossAmt = grossAmt;
        }

        public String getNetAmt() {
            return netAmt;
        }

        public void setNetAmt(String netAmt) {
            this.netAmt = netAmt;
        }

        public List<Sblvars> getSblvars() {
            return sblvars;
        }

        public void setSblvars(List<Sblvars> sblvars) {
            this.sblvars = sblvars;
        }
    public String getInvlat() {
        return invlat;
    }

    public void setInvlat(String invlat) {
        this.invlat = invlat;
    }

    public String getInvlong() {
        return invlong;
    }

    public void setInvlong(String invlong) {
        this.invlong = invlong;
    }

    public String getInvaddress() {
        return invaddress;
    }

    public void setInvaddress(String invaddress) {
        this.invaddress = invaddress;
    }


}

