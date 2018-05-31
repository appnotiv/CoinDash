package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DirectReferralResponse implements Serializable {
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
        @SerializedName("IncomeAmount")
        @Expose
        private String incomeAmount;
        @SerializedName("from_address")
        @Expose
        private String fromAddress;
        @SerializedName("InvestmentAmount")
        @Expose
        private String investmentAmount;
        @SerializedName("Percentage")
        @Expose
        private String percentage;
        @SerializedName("register_id")
        @Expose
        private String registerId;
        @SerializedName("FromUser")
        @Expose
        private String fromUser;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getIncomeAmount() {
            return incomeAmount;
        }

        public void setIncomeAmount(String incomeAmount) {
            this.incomeAmount = incomeAmount;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getInvestmentAmount() {
            return investmentAmount;
        }

        public void setInvestmentAmount(String investmentAmount) {
            this.investmentAmount = investmentAmount;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getRegisterId() {
            return registerId;
        }

        public void setRegisterId(String registerId) {
            this.registerId = registerId;
        }

        public String getFromUser() {
            return fromUser;
        }

        public void setFromUser(String fromUser) {
            this.fromUser = fromUser;
        }


    }

}
