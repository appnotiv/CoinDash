package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ROIHistoryResponse implements Serializable {
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
        @SerializedName("plan_id")
        @Expose
        private String planId;
        @SerializedName("InvestmentAmount")
        @Expose
        private String investmentAmount;
        @SerializedName("register_id")
        @Expose
        private String registerId;
        @SerializedName("PlanName")
        @Expose
        private String planName;

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

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public String getInvestmentAmount() {
            return investmentAmount;
        }

        public void setInvestmentAmount(String investmentAmount) {
            this.investmentAmount = investmentAmount;
        }

        public String getRegisterId() {
            return registerId;
        }

        public void setRegisterId(String registerId) {
            this.registerId = registerId;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }
    }

}
