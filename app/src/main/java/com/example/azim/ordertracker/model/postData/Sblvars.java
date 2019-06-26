package com.example.azim.ordertracker.model.postData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sblvars {

        @SerializedName("inv_no")
        @Expose
        private String invNo;
        @SerializedName("inv_dt")
        @Expose
        private String invDt;
        @SerializedName("srl_no")
        @Expose
        private String srlNo;
        @SerializedName("icode")
        @Expose
        private String icode;
        @SerializedName("qty")
        @Expose
        private String qty;
        @SerializedName("mrp")
        @Expose
        private String mrp;
        @SerializedName("rate")
        @Expose
        private String rate;
        @SerializedName("disc")
        @Expose
        private String disc;
        @SerializedName("disc_type")
        @Expose
        private String discType;
        @SerializedName("disc_amt")
        @Expose
        private String discAmt;

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

        public String getSrlNo() {
            return srlNo;
        }

        public void setSrlNo(String srlNo) {
            this.srlNo = srlNo;
        }

        public String getIcode() {
            return icode;
        }

        public void setIcode(String icode) {
            this.icode = icode;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getMrp() {
            return mrp;
        }

        public void setMrp(String mrp) {
            this.mrp = mrp;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDisc() {
            return disc;
        }

        public void setDisc(String disc) {
            this.disc = disc;
        }

        public String getDiscType() {
            return discType;
        }

        public void setDiscType(String discType) {
            this.discType = discType;
        }

        public String getDiscAmt() {
            return discAmt;
        }

        public void setDiscAmt(String discAmt) {
            this.discAmt = discAmt;
        }

    }