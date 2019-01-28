package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ageech@live.com
 */

public class TVGuideReminder extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("channel_id")
    private int channelId;

    @SerializedName("epg_channel_id")
    private int epgChannelId;

    @SerializedName("epg_programme_id")
    private String epgProgrammeId;

    @SerializedName("activate_time")
    private String activateTime;

    @SerializedName("activate_time_timestamp")
    private long activateTimeTimestamp;

    @SerializedName("string_id")
    private String stringId;

    @SerializedName("epg_programme_title")
    private String epgProgrammeTitle;

    @SerializedName("epg_programme")
    private TVGuideReminderProgramme epgProgramme;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getEpgChannelId() {
        return epgChannelId;
    }

    public void setEpgChannelId(int epgChannelId) {
        this.epgChannelId = epgChannelId;
    }

    public String getEpgProgrammeId() {
        return epgProgrammeId;
    }

    public void setEpgProgrammeId(String epgProgrammeId) {
        this.epgProgrammeId = epgProgrammeId;
    }

    public String getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(String activateTime) {
        this.activateTime = activateTime;
    }

    public long getActivateTimeTimestamp() {
        return activateTimeTimestamp;
    }

    public void setActivateTimeTimestamp(long activateTimeTimestamp) {
        this.activateTimeTimestamp = activateTimeTimestamp;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getEpgProgrammeTitle() {
        return epgProgrammeTitle;
    }

    public void setEpgProgrammeTitle(String epgProgrammeTitle) {
        this.epgProgrammeTitle = epgProgrammeTitle;
    }

    public TVGuideReminderProgramme getEpgProgramme() {
        return epgProgramme;
    }

    public void setEpgProgramme(TVGuideReminderProgramme epgProgramme) {
        this.epgProgramme = epgProgramme;
    }
}
