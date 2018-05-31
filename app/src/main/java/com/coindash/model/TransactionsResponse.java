package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class TransactionsResponse implements Serializable {
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
        @SerializedName("Send")
        @Expose
        private String send;
        @SerializedName("Receive")
        @Expose
        private String receive;
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

        public String getSend() {
            return send;
        }

        public void setSend(String send) {
            this.send = send;
        }

        public String getReceive() {
            return receive;
        }

        public void setReceive(String receive) {
            this.receive = receive;
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
