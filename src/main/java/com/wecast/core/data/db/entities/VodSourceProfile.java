package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class VodSourceProfile extends RealmObject {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("minimum_bitrate")
    private String minimumBitrate;

    @SerializedName("maximum_bitrate")
    private String maximumBitrate;

    @SerializedName("code")
    private int code;

    @SerializedName("business_model")
    private String businessModel;

    @SerializedName("subscribed")
    private boolean subscribed;

    @SerializedName("pricing")
    private RealmList<VodSourceProfilePricing> pricing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinimumBitrate() {
        return minimumBitrate;
    }

    public void setMinimumBitrate(String minimumBitrate) {
        this.minimumBitrate = minimumBitrate;
    }

    public String getMaximumBitrate() {
        return maximumBitrate;
    }

    public void setMaximumBitrate(String maximumBitrate) {
        this.maximumBitrate = maximumBitrate;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public RealmList<VodSourceProfilePricing> getPricing() {
        return pricing;
    }

    public void setPricing(RealmList<VodSourceProfilePricing> pricing) {
        this.pricing = pricing;
    }
}
