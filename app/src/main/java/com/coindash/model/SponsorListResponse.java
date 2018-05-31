package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SponsorListResponse implements Serializable {
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

        @SerializedName("JoiningDate")
        @Expose
        private String joiningDate;
        @SerializedName("UserName")
        @Expose
        private String userName;
        @SerializedName("EmailID")
        @Expose
        private String emailID;
        @SerializedName("Country")
        @Expose
        private String country;
        @SerializedName("MobileNo")
        @Expose
        private String mobileNo;
        @SerializedName("ActiveInvestment")
        @Expose
        private String activeInvestment;

        public String getJoiningDate() {
            return joiningDate;
        }

        public void setJoiningDate(String joiningDate) {
            this.joiningDate = joiningDate;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmailID() {
            return emailID;
        }

        public void setEmailID(String emailID) {
            this.emailID = emailID;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getActiveInvestment() {
            return activeInvestment;
        }

        public void setActiveInvestment(String activeInvestment) {
            this.activeInvestment = activeInvestment;
        }
    }

}
