package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class VodSource extends RealmObject {

    @SerializedName("url")
    private String url;

    @SerializedName("drm_license_url")
    private String drmLicenseUrl;

    @SerializedName("subtitles")
    private RealmList<VodSubtitle> subtitles;

    @SerializedName("audio_tracks")
    private RealmList<VodAudioTrack> audioTracks;

    @SerializedName("profiles")
    private RealmList<VodSourceProfile> profiles;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDrmLicenseUrl() {
        return drmLicenseUrl;
    }

    public void setDrmLicenseUrl(String drmLicenseUrl) {
        this.drmLicenseUrl = drmLicenseUrl;
    }

    public RealmList<VodSubtitle> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(RealmList<VodSubtitle> subtitles) {
        this.subtitles = subtitles;
    }

    public RealmList<VodAudioTrack> getAudioTracks() {
        return audioTracks;
    }

    public void setAudioTracks(RealmList<VodAudioTrack> audioTracks) {
        this.audioTracks = audioTracks;
    }

    public RealmList<VodSourceProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(RealmList<VodSourceProfile> profiles) {
        this.profiles = profiles;
    }
}
