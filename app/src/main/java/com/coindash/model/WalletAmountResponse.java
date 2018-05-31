package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletAmountResponse {
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
        @SerializedName("PBZWallet")
        @Expose
        private String pBZWallet;
        @SerializedName("BTCWallet")
        @Expose
        private String bTCWallet;
        @SerializedName("InvestmentWallet")
        @Expose
        private String investmentWallet;
        @SerializedName("EarningWallet")
        @Expose
        private String earningWallet;
        @SerializedName("PBZAddress")
        @Expose
        private String pBZAddress;
        @SerializedName("BTCAddress")
        @Expose
        private String bTCAddress;
        @SerializedName("right_amount")
        @Expose
        private String rightAmount;
        @SerializedName("left_amount")
        @Expose
        private String leftAmount;
        @SerializedName("SendFees")
        @Expose
        private String sendFees;
        @SerializedName("WithdrawFees")
        @Expose
        private String withdrawFees;
        @SerializedName("Price")
        @Expose
        private String price;
        @SerializedName("TotalBinaryTeam")
        @Expose
        private String totalBinaryTeam;
        @SerializedName("TotalLeftUser")
        @Expose
        private String totalLeftUser;
        @SerializedName("TotalRightUser")
        @Expose
        private String totalRightUser;
        @SerializedName("TotalROI")
        @Expose
        private String totalROI;
        @SerializedName("TotalRightBusiness")
        @Expose
        private String totalRightBusiness;
        @SerializedName("TotalLeftBusiness")
        @Expose
        private String totalLeftBusiness;
        @SerializedName("TotalDirectUser")
        @Expose
        private String totalDirectUser;
        @SerializedName("TotalInvestment")
        @Expose
        private String totalInvestment;
        @SerializedName("TotalBinaryIncome")
        @Expose
        private String totalBinaryIncome;

        public String getPBZWallet() {
            return pBZWallet;
        }

        public void setPBZWallet(String pBZWallet) {
            this.pBZWallet = pBZWallet;
        }

        public String getBTCWallet() {
            return bTCWallet;
        }

        public void setBTCWallet(String bTCWallet) {
            this.bTCWallet = bTCWallet;
        }

        public String getInvestmentWallet() {
            return investmentWallet;
        }

        public void setInvestmentWallet(String investmentWallet) {
            this.investmentWallet = investmentWallet;
        }

        public String getEarningWallet() {
            return earningWallet;
        }

        public void setEarningWallet(String earningWallet) {
            this.earningWallet = earningWallet;
        }

        public String getPBZAddress() {
            return pBZAddress;
        }

        public void setPBZAddress(String pBZAddress) {
            this.pBZAddress = pBZAddress;
        }

        public String getBTCAddress() {
            return bTCAddress;
        }

        public void setBTCAddress(String bTCAddress) {
            this.bTCAddress = bTCAddress;
        }

        public String getRightAmount() {
            return rightAmount;
        }

        public void setRightAmount(String rightAmount) {
            this.rightAmount = rightAmount;
        }

        public String getLeftAmount() {
            return leftAmount;
        }

        public void setLeftAmount(String leftAmount) {
            this.leftAmount = leftAmount;
        }

        public String getSendFees() {
            return sendFees;
        }

        public void setSendFees(String sendFees) {
            this.sendFees = sendFees;
        }

        public String getWithdrawFees() {
            return withdrawFees;
        }

        public void setWithdrawFees(String withdrawFees) {
            this.withdrawFees = withdrawFees;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotalBinaryTeam() {
            return totalBinaryTeam;
        }

        public void setTotalBinaryTeam(String totalBinaryTeam) {
            this.totalBinaryTeam = totalBinaryTeam;
        }

        public String getTotalLeftUser() {
            return totalLeftUser;
        }

        public void setTotalLeftUser(String totalLeftUser) {
            this.totalLeftUser = totalLeftUser;
        }

        public String getTotalRightUser() {
            return totalRightUser;
        }

        public void setTotalRightUser(String totalRightUser) {
            this.totalRightUser = totalRightUser;
        }

        public String getTotalROI() {
            return totalROI;
        }

        public void setTotalROI(String totalROI) {
            this.totalROI = totalROI;
        }

        public String getTotalRightBusiness() {
            return totalRightBusiness;
        }

        public void setTotalRightBusiness(String totalRightBusiness) {
            this.totalRightBusiness = totalRightBusiness;
        }

        public String getTotalLeftBusiness() {
            return totalLeftBusiness;
        }

        public void setTotalLeftBusiness(String totalLeftBusiness) {
            this.totalLeftBusiness = totalLeftBusiness;
        }

        public String getTotalDirectUser() {
            return totalDirectUser;
        }

        public void setTotalDirectUser(String totalDirectUser) {
            this.totalDirectUser = totalDirectUser;
        }

        public String getTotalInvestment() {
            return totalInvestment;
        }

        public void setTotalInvestment(String totalInvestment) {
            this.totalInvestment = totalInvestment;
        }

        public String getTotalBinaryIncome() {
            return totalBinaryIncome;
        }

        public void setTotalBinaryIncome(String totalBinaryIncome) {
            this.totalBinaryIncome = totalBinaryIncome;
        }
    }
}
