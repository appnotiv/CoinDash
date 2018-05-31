package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GradeBonusResponse implements Serializable {
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
        @SerializedName("Bonus")
        @Expose
        private String bonus;
        @SerializedName("MatchingAmount")
        @Expose
        private String matchingAmount;
        @SerializedName("Percentage")
        @Expose
        private String percentage;
        @SerializedName("TotalIncome")
        @Expose
        private String totalIncome;
        @SerializedName("Level")
        @Expose
        private String level;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("FromUser")
        @Expose
        private String fromUser;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFromUser() {
            return fromUser;
        }

        public void setFromUser(String fromUser) {
            this.fromUser = fromUser;
        }
    }

}
