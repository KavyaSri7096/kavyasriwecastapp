package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ageech@live.com
 */

public class TVGuideReminderProgramme extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("epg_channel_id")
    private int epgChannelId;

    @SerializedName("string_id")
    private String stringId;

    @SerializedName("title")
    private String title;

    @SerializedName("start")
    private String start;

    @SerializedName("stop")
    private String stop;

    @SerializedName("start_timestamp")
    private long startTimestamp;

    @SerializedName("stop_timestamp")
    private long stopTimestamp;

    @SerializedName("description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEpgChannelId() {
        return epgChannelId;
    }

    public void setEpgChannelId(int epgChannelId) {
        this.epgChannelId = epgChannelId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getStopTimestamp() {
        return stopTimestamp;
    }

    public void setStopTimestamp(long stopTimestamp) {
        this.stopTimestamp = stopTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
