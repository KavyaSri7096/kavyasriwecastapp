package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class BannerAdsFile extends RealmObject {

    @SerializedName("original")
    private String original;

    @SerializedName("original_970_90")
    private String original_970_90;

    @SerializedName("original_300_250")
    private String original_300_250;

    @SerializedName("original_320_100")
    private String original_320_100;

    @SerializedName("original_200_200")
    private String original_200_200;

    @SerializedName("original_160_600")
    private String original_160_600;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginal_970_90() {
        return original_970_90;
    }

    public void setOriginal_970_90(String original_970_90) {
        this.original_970_90 = original_970_90;
    }

    public String getOriginal_300_250() {
        return original_300_250;
    }

    public void setOriginal_300_250(String original_300_250) {
        this.original_300_250 = original_300_250;
    }

    public String getOriginal_320_100() {
        return original_320_100;
    }

    public void setOriginal_320_100(String original_320_100) {
        this.original_320_100 = original_320_100;
    }

    public String getOriginal_200_200() {
        return original_200_200;
    }

    public void setOriginal_200_200(String original_200_200) {
        this.original_200_200 = original_200_200;
    }

    public String getOriginal_160_600() {
        return original_160_600;
    }

    public void setOriginal_160_600(String original_160_600) {
        this.original_160_600 = original_160_600;
    }
}