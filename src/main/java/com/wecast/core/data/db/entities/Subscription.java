package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ageech@live.com
 */

public class Subscription {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("login_policy")
    private boolean loginPolicy;

    @SerializedName("countries_blocking")
    private boolean countriesBlocking;

    @SerializedName("currency_code")
    private String currencyCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoginPolicy() {
        return loginPolicy;
    }

    public void setLoginPolicy(boolean loginPolicy) {
        this.loginPolicy = loginPolicy;
    }

    public boolean isCountriesBlocking() {
        return countriesBlocking;
    }

    public void setCountriesBlocking(boolean countriesBlocking) {
        this.countriesBlocking = countriesBlocking;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}