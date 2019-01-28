package com.wecast.core.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ageech@live.com
 */


public class ResponseModel<T> implements Serializable {

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccessful() {
        return status > 0;
    }

    public boolean isSubscriptionExpired() {
        return status == 2;
    }

    public boolean isTokenExpired() {
        return status < 0;
    }
}
