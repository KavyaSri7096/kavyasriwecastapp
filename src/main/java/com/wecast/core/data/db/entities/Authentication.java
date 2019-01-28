package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ageech@live.com
 */

public class Authentication {

    @SerializedName("profile")
    private Profile profile;

    @SerializedName("account")
    private Account account;

    @SerializedName("device")
    private Device device;

    @SerializedName("subscription")
    private Subscription subscription;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}