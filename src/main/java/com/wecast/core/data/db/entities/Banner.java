package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class Banner extends RealmObject {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("ads_file")
    private BannerAdsFile adsFile;

    @SerializedName("ads_file_dir")
    private String adsFileDir;

    @SerializedName("string_value")
    private String stringValue;

    @SerializedName("target_url")
    private String targetUrl;

    @SerializedName("ads_type")
    private String adsType;

    @SerializedName("modified")
    private String modified;

    @SerializedName("created")
    private String created;

    @SerializedName("ads_banner_campaigns")
    private BannerCampaign adsBannerCampaigns;

    @SerializedName("content")
    private String content;

    @SerializedName("mongo_id")
    private String mongoId;

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

    public BannerAdsFile getAdsFile() {
        return adsFile;
    }

    public void setAdsFile(BannerAdsFile adsFile) {
        this.adsFile = adsFile;
    }

    public String getAdsFileDir() {
        return adsFileDir;
    }

    public void setAdsFileDir(String adsFileDir) {
        this.adsFileDir = adsFileDir;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getAdsType() {
        return adsType;
    }

    public void setAdsType(String adsType) {
        this.adsType = adsType;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public BannerCampaign getAdsBannerCampaigns() {
        return adsBannerCampaigns;
    }

    public void setAdsBannerCampaigns(BannerCampaign adsBannerCampaigns) {
        this.adsBannerCampaigns = adsBannerCampaigns;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getBoxPosition() {
        String boxPosition = null;
        if (getAdsBannerCampaigns() != null) {
            boxPosition = getAdsBannerCampaigns().getBoxPosition();
        }
        return boxPosition;
    }
}
