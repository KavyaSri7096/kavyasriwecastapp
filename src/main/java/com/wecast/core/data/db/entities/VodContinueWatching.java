package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class VodContinueWatching extends RealmObject {

    @SerializedName("finished")
    private boolean finished;

    @SerializedName("stopped_time")
    private int stoppedTime;

    @SerializedName("duration")
    private float duration;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getStoppedTime() {
        return stoppedTime;
    }

    public void setStoppedTime(int stoppedTime) {
        this.stoppedTime = stoppedTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
