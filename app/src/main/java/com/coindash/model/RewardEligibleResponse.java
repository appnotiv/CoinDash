package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class RewardEligibleResponse implements Serializable {
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
        @SerializedName("LeftBusiness")
        @Expose
        private String leftBusiness;
        @SerializedName("RightBusiness")
        @Expose
        private String rightBusiness;
        @SerializedName("Description")
        @Expose
        private String description;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLeftBusiness() {
            return leftBusiness;
        }

        public void setLeftBusiness(String leftBusiness) {
            this.leftBusiness = leftBusiness;
        }

        public String getRightBusiness() {
            return rightBusiness;
        }

        public void setRightBusiness(String rightBusiness) {
            this.rightBusiness = rightBusiness;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

}
