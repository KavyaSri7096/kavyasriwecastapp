package com.wecast.core.data.db.entities.composer;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class LauncherIcons extends RealmObject {

    @SerializedName("mdpi")
    private String mdpi;

    @SerializedName("hdpi")
    private String hdpi;

    @SerializedName("xhdpi")
    private String xhdpi;

    @SerializedName("xxhdpi")
    private String xxhdpi;

    @SerializedName("xxxhdpi")
    private String xxxhdpi;

    public String getMdpi() {
        return mdpi;
    }

    public void setMdpi(String mdpi) {
        this.mdpi = mdpi;
    }

    public String getHdpi() {
        return hdpi;
    }

    public void setHdpi(String hdpi) {
        this.hdpi = hdpi;
    }

    public String getXhdpi() {
        return xhdpi;
    }

    public void setXhdpi(String xhdpi) {
        this.xhdpi = xhdpi;
    }

    public String getXxhdpi() {
        return xxhdpi;
    }

    public void setXxhdpi(String xxhdpi) {
        this.xxhdpi = xxhdpi;
    }

    public String getXxxhdpi() {
        return xxxhdpi;
    }

    public void setXxxhdpi(String xxxhdpi) {
        this.xxxhdpi = xxxhdpi;
    }
}
