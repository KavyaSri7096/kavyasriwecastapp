package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class ChannelStreamingProfile extends RealmObject {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("minimumBitrate")
    private int minimumBitrate;

    @SerializedName("maximumBitrate")
    private int maximumBitrate;

    public ChannelStreamingProfile() {
    }

    public ChannelStreamingProfile(int id, String name, int minimum_bitrate, int maximum_bitrate) {
        this.id = id;
        this.name = name;
        this.minimumBitrate = minimum_bitrate;
        this.maximumBitrate = maximum_bitrate;
    }

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

    public int getMinimumBitratee() {
        return minimumBitrate;
    }

    public void setMinimumBitratee(int minimumBitrate) {
        this.minimumBitrate = minimumBitrate;
    }

    public int getMaximumBitrate() {
        return maximumBitrate;
    }

    public void setMaximumBitrate(int maximum_bitrate) {
        this.maximumBitrate = maximum_bitrate;
    }
}
