package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class ChannelProfile extends RealmObject {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("minimum_bitrate")
    private int minimumBitrate;

    @SerializedName("maximum_bitrate")
    private int maximumBitrate;

    @SerializedName("subscribed")
    private boolean subscribed;

    @SerializedName("price")
    private String price;

    @SerializedName("duration")
    private String duration;

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

    public int getMinimumBitrate() {
        return minimumBitrate;
    }

    public void setMinimumBitrate(int minimumBitrate) {
        this.minimumBitrate = minimumBitrate;
    }

    public int getMaximumBitrate() {
        return maximumBitrate;
    }

    public void setMaximumBitrate(int maximumBitrate) {
        this.maximumBitrate = maximumBitrate;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
