package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class Rated extends RealmObject {

    @SerializedName("id")
    private int id;

    @SerializedName("model")
    private String model;

    @SerializedName("modelId")
    private int modelId;

    @SerializedName("rate")
    private int rate;

    @SerializedName("accountId")
    private int accountId;

    @SerializedName("accountProfileId")
    private int accountProfileId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int model_id) {
        this.modelId = model_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int account_id) {
        this.accountId = account_id;
    }

    public int getAccountProfileId() {
        return accountProfileId;
    }

    public void setAccountProfileId(int account_profile_id) {
        this.accountProfileId = account_profile_id;
    }
}
