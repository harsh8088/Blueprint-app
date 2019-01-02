package com.example.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chef {
    @SerializedName("status")
    @Expose
    Boolean status;
    @SerializedName("msg")
    @Expose
    String msg;
    @SerializedName("data")
    @Expose
    Data data;

    /**
     * No args constructor for use in serialization
     */
    public Chef() {
    }

    /**
     * @param status
     * @param data
     * @param msg
     */
    public Chef(Boolean status, String msg, Data data) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
