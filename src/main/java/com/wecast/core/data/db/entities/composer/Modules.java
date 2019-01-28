package com.wecast.core.data.db.entities.composer;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class Modules extends RealmObject {

    @SerializedName("registration")
    private boolean registration = true;

    @SerializedName("home")
    private boolean home = true;

    @SerializedName("trending")
    private boolean trending = true;

    @SerializedName("channels")
    private boolean channels = true;

    @SerializedName("epg")
    private boolean epg = true;

    @SerializedName("vod")
    private boolean vod = true;

    @SerializedName("subscriptions")
    private boolean subscriptions = true;

    @SerializedName("advertisements")
    private boolean advertisements = true;

    public boolean hasRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public boolean hasHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public boolean hasTrending() {
        return trending;
    }

    public void setTrending(boolean trending) {
        this.trending = trending;
    }

    public boolean hasChannels() {
        return channels;
    }

    public void setChannels(boolean channels) {
        this.channels = channels;
    }

    public boolean hasEpg() {
        return epg;
    }

    public void setEpg(boolean epg) {
        this.epg = epg;
    }

    public boolean hasVod() {
        return vod;
    }

    public void setVod(boolean vod) {
        this.vod = vod;
    }


    public boolean hasSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(boolean subscriptions) {
        this.subscriptions = subscriptions;
    }

    public boolean hasAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(boolean advertisements) {
        this.advertisements = advertisements;
    }

}
