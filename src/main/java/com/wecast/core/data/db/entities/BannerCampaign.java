package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class BannerCampaign extends RealmObject {

    @SerializedName("id")
    private int id;

    @SerializedName("ads_parameter")
    private String adsParameter;

    @SerializedName("ads_type")
    private String adsType;

    @SerializedName("start")
    private String start;

    @SerializedName("end")
    private String end;

    @SerializedName("display_from_time")
    private String displayFromTime;

    @SerializedName("display_to_time")
    private String displayToTime;

    @SerializedName("box_position")
    private String boxPosition;

    @SerializedName("status")
    private boolean status;

    @SerializedName("devices")
    private String devices;

    @SerializedName("created")
    private String created;

    @SerializedName("modified")
    private String modified;

    @SerializedName("created_by")
    private int createdBy;

    @SerializedName("updated_by")
    private int updatedBy;

    @SerializedName("city_count")
    private int cityCount;

    @SerializedName("country_count")
    private int countryCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdsParameter() {
        return adsParameter;
    }

    public void setAdsParameter(String adsParameter) {
        this.adsParameter = adsParameter;
    }

    public String getAdsType() {
        return adsType;
    }

    public void setAdsType(String adsType) {
        this.adsType = adsType;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDisplayFromTime() {
        return displayFromTime;
    }

    public void setDisplayFromTime(String displayFromTime) {
        this.displayFromTime = displayFromTime;
    }

    public String getDisplayToTime() {
        return displayToTime;
    }

    public void setDisplayToTime(String displayToTime) {
        this.displayToTime = displayToTime;
    }

    public String getBoxPosition() {
        return boxPosition;
    }

    public void setBoxPosition(String boxPosition) {
        this.boxPosition = boxPosition;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getCityCount() {
        return cityCount;
    }

    public void setCityCount(int cityCount) {
        this.cityCount = cityCount;
    }

    public int getCountryCount() {
        return countryCount;
    }

    public void setCountryCount(int countryCount) {
        this.countryCount = countryCount;
    }
}
