package com.wecast.core.data.db.entities.composer;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class Android extends RealmObject {

    @SerializedName("launcher_icons")
    private LauncherIcons launcherIcons;

    @SerializedName("mobile")
    private Mobile mobile;

    public LauncherIcons getLauncherIcons() {
        return launcherIcons;
    }

    public void setLauncherIcons(LauncherIcons launcherIcons) {
        this.launcherIcons = launcherIcons;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }
}
