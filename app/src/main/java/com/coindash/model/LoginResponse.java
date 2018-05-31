package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse {
    @SerializedName("status")
    @Expose
    private Integer success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("info")
    @Expose
    private Info info;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public class Info {

        @SerializedName("fullname")
        @Expose
        private String fullname;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("email_id")
        @Expose
        private String emailId;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("register_date")
        @Expose
        private String registerDate;
        @SerializedName("register_id")
        @Expose
        private String registerId;
        @SerializedName("login_status")
        @Expose
        private String loginStatus;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("country_code")
        @Expose
        private String countryCode;
        @SerializedName("profile_image")
        @Expose
        private String profileImage;
        @SerializedName("email_verification_status")
        @Expose
        private String emailVerificationStatus;
        @SerializedName("resident_address")
        @Expose
        private String residentAddress;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("pincode")
        @Expose
        private String pincode;
        @SerializedName("Profile")
        @Expose
        private String profile;

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getRegisterId() {
            return registerId;
        }

        public void setRegisterId(String registerId) {
            this.registerId = registerId;
        }

        public String getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(String loginStatus) {
            this.loginStatus = loginStatus;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getEmailVerificationStatus() {
            return emailVerificationStatus;
        }

        public void setEmailVerificationStatus(String emailVerificationStatus) {
            this.emailVerificationStatus = emailVerificationStatus;
        }

        public String getResidentAddress() {
            return residentAddress;
        }

        public void setResidentAddress(String residentAddress) {
            this.residentAddress = residentAddress;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

    }
}
