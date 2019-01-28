package com.wecast.core.data.db.entities.composer;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ageech@live.com
 */

public class Composer extends RealmObject {

    @PrimaryKey
    @SerializedName("app_name")
    private String appName;

    @SerializedName("theme")
    private String theme;

    @SerializedName("socket_url")
    private String socketUrl;

    @SerializedName("android")
    private Android android;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSocketUrl() {
        return socketUrl;
    }

    public void setSocketUrl(String socketUrl) {
        this.socketUrl = socketUrl;
    }

    public Android getAndroid() {
        return android;
    }

    public void setAndroid(Android android) {
        this.android = android;
    }
}
