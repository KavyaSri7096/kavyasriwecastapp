package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class TVShowSeason extends RealmObject {

    @SerializedName("id")
    private int id;

    @SerializedName("number")
    private int number;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
