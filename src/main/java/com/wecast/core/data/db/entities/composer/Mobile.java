package com.wecast.core.data.db.entities.composer;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class Mobile extends RealmObject {

    @SerializedName("logos")
    private LogoIcons logos;

    @SerializedName("modules")
    private Modules modules;

    public LogoIcons getLogos() {
        return logos;
    }

    public void setLogos(LogoIcons logos) {
        this.logos = logos;
    }

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }
}
