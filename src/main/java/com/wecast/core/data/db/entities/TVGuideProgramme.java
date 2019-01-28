package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by: ageech@live.com
 */

public class TVGuideProgramme extends RealmObject {

    @SerializedName("id")
    private String id;

    @SerializedName("string_id")
    private String stringId;

    @SerializedName("start")
    private long start;

    @SerializedName("stop")
    private long stop;

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getStop() {
        return stop;
    }

    public void setStop(long stop) {
        this.stop = stop;
    }

    public Date getStartDate() {
        Calendar calendar = Calendar.getInstance();
        //int timeOffset = (TimeZone.getDefault().getOffset(calendar.getTimeInMillis())) / 1000 / 60 / 60;
        Date startDate = new Date(start * 1000);
        calendar.setTime(startDate);
        //calendar.add(Calendar.HOUR_OF_DAY, timeOffset);
        return calendar.getTime();
    }

    public Date getStopDate() {
        Calendar calendar = Calendar.getInstance();
        //int timeOffset = (TimeZone.getDefault().getOffset(calendar.getTimeInMillis())) / 1000 / 60 / 60;
        Date stopDate = new Date(stop * 1000);
        calendar.setTime(stopDate);
        //calendar.add(Calendar.HOUR_OF_DAY, timeOffset);
        return calendar.getTime();
    }

    /**
     * Created by: ageech@live.com
     */

    public boolean isCurrent() {
        long now = System.currentTimeMillis();
        long start = getStartDate().getTime();
        long stop = getStopDate().getTime();
        return now >= start && now <= stop;
    }

    public int getDurationMin() {
        if (getStartDate() == null || getStopDate() == null) return 0;

        long startTime = getStartDate().getTime();
        long stopTime = getStopDate().getTime();
        long durationMin = (stopTime - startTime) / 1000 / 60;
        return (int) durationMin;
    }

    public int getCurrentMinute() {
        if (getStartDate() == null) return 0;

        long startTime = getStartDate().getTime();
        long currentTimeMin = (System.currentTimeMillis() - startTime) / 1000 / 60;
        return (int) currentTimeMin;
    }
}

