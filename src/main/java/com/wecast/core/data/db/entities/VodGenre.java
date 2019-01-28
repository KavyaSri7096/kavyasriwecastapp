package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ageech@live.com
 */

public class VodGenre extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("parent_id")
    private int parentId;

    @SerializedName("lft")
    private int lft;

    @SerializedName("rght")
    private int rght;

    @SerializedName("icon_url")
    private String iconUrl;

    @SerializedName("background_url")
    private String backgroundUrl;

    @SerializedName("iconResource")
    private int iconResource;

    @SerializedName("iconActiveResource")
    private int iconActiveResource;

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public int getRght() {
        return rght;
    }

    public void setRght(int rght) {
        this.rght = rght;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }

    public int getIconActiveResource() {
        return iconActiveResource;
    }

    public void setIconActiveResource(int iconActiveResource) {
        this.iconActiveResource = iconActiveResource;
    }
}