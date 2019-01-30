package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class PaymentHistory extends RealmObject {

    @SerializedName("id")
    private int id;

    @SerializedName("account_id")
    private int accountId;

    @SerializedName("account_profile_id")
    private int accountProfileId;

    @SerializedName("market_id")
    private int marketId;

    @SerializedName("price")
    private float price;

    @SerializedName("paid")
    private boolean paid;

    @SerializedName("created")
    private String created;

    @SerializedName("ip_address")
    private String ipAddress;

    @SerializedName("record_title")
    private String recordTitle;

    @SerializedName("record_model")
    private String recordModel;

    @SerializedName("record_model_id")
    private String recordModelId;

    @SerializedName("description")
    private String description;

    @SerializedName("currency")
    private String currency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountProfileId() {
        return accountProfileId;
    }

    public void setAccountProfileId(int accountProfileId) {
        this.accountProfileId = accountProfileId;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getRecordModel() {
        return recordModel;
    }

    public void setRecordModel(String recordModel) {
        this.recordModel = recordModel;
    }

    public String getRecordModelId() {
        return recordModelId;
    }

    public void setRecordModelId(String recordModelId) {
        this.recordModelId = recordModelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
