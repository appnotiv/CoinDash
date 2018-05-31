package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InvestmentHistoryResponse {
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
        @SerializedName("ActiveAmount")
        @Expose
        private String activeAmount;
        @SerializedName("ExpiredAmount")
        @Expose
        private String expiredAmount;
        @SerializedName("PackageName")
        @Expose
        private String packageName;
        @SerializedName("Status")
        @Expose
        private String status;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getActiveAmount() {
            return activeAmount;
        }

        public void setActiveAmount(String activeAmount) {
            this.activeAmount = activeAmount;
        }

        public String getExpiredAmount() {
            return expiredAmount;
        }

        public void setExpiredAmount(String expiredAmount) {
            this.expiredAmount = expiredAmount;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
