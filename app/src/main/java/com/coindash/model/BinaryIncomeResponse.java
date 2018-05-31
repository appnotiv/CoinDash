package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BinaryIncomeResponse implements Serializable {
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
        @SerializedName("NetIncome")
        @Expose
        private String netIncome;
        @SerializedName("MatchingAmount")
        @Expose
        private String matchingAmount;
        @SerializedName("Percentage")
        @Expose
        private String percentage;
        @SerializedName("TotalIncome")
        @Expose
        private String totalIncome;
        @SerializedName("Capping")
        @Expose
        private String capping;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getNetIncome() {
            return netIncome;
        }

        public void setNetIncome(String netIncome) {
            this.netIncome = netIncome;
        }

        public String getMatchingAmount() {
            return matchingAmount;
        }

        public void setMatchingAmount(String matchingAmount) {
            this.matchingAmount = matchingAmount;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getTotalIncome() {
            return totalIncome;
        }

        public void setTotalIncome(String totalIncome) {
            this.totalIncome = totalIncome;
        }

        public String getCapping() {
            return capping;
        }

        public void setCapping(String capping) {
            this.capping = capping;
        }

    }

}
