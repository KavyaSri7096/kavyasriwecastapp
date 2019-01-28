package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class Language extends RealmObject {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("shortCode")
    private String shortCode;

    public Language() {
    }

    public Language(int id, String name, String shortCode) {
        this.id = id;
        this.name = name;
        this.shortCode = shortCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String short_code) {
        this.shortCode = short_code;
    }
}
