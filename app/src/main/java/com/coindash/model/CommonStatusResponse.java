package com.coindash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonStatusResponse {
    @SerializedName("status")
    @Expose
    private Integer success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("info")
    @Expose
    private Object info;

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

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
