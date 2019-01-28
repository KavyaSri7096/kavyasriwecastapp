package com.wecast.core.data.db.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ageech@live.com
 */

public class TVGuide extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("channel_number")
    private int channelNumber;

    @SerializedName("epg_channel_id")
    private int epgChannelId;

    @SerializedName("logo")
    @Expose
    private String logo;

    @SerializedName("parental_rating")
    private ParentalRating parentalRating;

    @SerializedName("programmes")
    @Expose
    private RealmList<TVGuideProgramme> programmes;

    private long timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    public int getEpgChannelId() {
        return epgChannelId;
    }

    public void setEpgChannelId(int epgChannelId) {
        this.epgChannelId = epgChannelId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ParentalRating getParentalRating() {
        return parentalRating;
    }

    public void setParentalRating(ParentalRating parentalRating) {
        this.parentalRating = parentalRating;
    }

    public RealmList<TVGuideProgramme> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(RealmList<TVGuideProgramme> programmes) {
        this.programmes = programmes;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
