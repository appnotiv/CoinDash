package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class EarningWithdrawHistoryResponse implements Serializable {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("info")
    @Expose
    private ArrayList<Info> info;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<Info> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Info> info) {
        this.info = info;
    }

    public class Info {

        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("AmountUSD")
        @Expose
        private String amountUSD;
        @SerializedName("AmountPBZ")
        @Expose
        private String amountPBZ;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("Status")
        @Expose
        private String status;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAmountUSD() {
            return amountUSD;
        }

        public void setAmountUSD(String amountUSD) {
            this.amountUSD = amountUSD;
        }

        public String getAmountPBZ() {
            return amountPBZ;
        }

        public void setAmountPBZ(String amountPBZ) {
            this.amountPBZ = amountPBZ;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

}
