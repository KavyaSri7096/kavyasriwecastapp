package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ageech@live.com
 */

public class Channel extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("channel_number")
    private int channelNumber;

    @SerializedName("language")
    private String language;

    @SerializedName("epg_channel_id")
    private int epgChannelId;

    @SerializedName("backup_enabled")
    private boolean backupEnabled;

    @SerializedName("catchup_enabled")
    private boolean catchupEnabled;

    @SerializedName("time_shift_enabled")
    private boolean timeShiftEnabled;

    @SerializedName("channel_categories")
    private RealmList<ChannelGenre> channelCategories;

    @SerializedName("subscribed")
    private boolean subscribed;

    @SerializedName("primary_url")
    private String primaryUrl;

    @SerializedName("backup_url")
    private String backupUrl;

    @SerializedName("catchup_url")
    private String catchupUrl;

    @SerializedName("type")
    private String type;

    @SerializedName("logo_url")
    private String logoUrl;

    @SerializedName("screenshot_url")
    private String screenShotUrl;

    @SerializedName("catchup_duration_total")
    private Integer catchupDurationTotal;

    @SerializedName("catchup_url_params")
    private ChannelUrlParams catchupURLParams;

    @SerializedName("time_shifted_streams")
    private RealmList<ChannelTimeShiftStream> timeShiftedStreams;

    @SerializedName("parental_rating")
    private ParentalRating parentalRating;

    @SerializedName("favorite")
    private boolean favorite;

    @SerializedName("profiles")
    private RealmList<ChannelProfile> profiles;

    private boolean isTrending;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getEpgChannelId() {
        return epgChannelId;
    }

    public void setEpgChannelId(int epgChannelId) {
        this.epgChannelId = epgChannelId;
    }

    public boolean isBackupEnabled() {
        return backupEnabled;
    }

    public void setBackupEnabled(boolean backupEnabled) {
        this.backupEnabled = backupEnabled;
    }

    public boolean isCatchupEnabled() {
        return catchupEnabled;
    }

    public void setCatchupEnabled(boolean catchupEnabled) {
        this.catchupEnabled = catchupEnabled;
    }

    public boolean isTimeShiftEnabled() {
        return timeShiftEnabled;
    }

    public void setTimeShiftEnabled(boolean timeShiftEnabled) {
        this.timeShiftEnabled = timeShiftEnabled;
    }

    public RealmList<ChannelGenre> getCategories() {
        return channelCategories;
    }

    public void setCategories(RealmList<ChannelGenre> channelCategories) {
        this.channelCategories = channelCategories;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String getPrimaryUrl() {
        return primaryUrl;
    }

    public void setPrimaryUrl(String primaryUrl) {
        this.primaryUrl = primaryUrl;
    }

    public String getBackupUrl() {
        return backupUrl;
    }

    public void setBackupUrl(String backupUrl) {
        this.backupUrl = backupUrl;
    }

    public String getCatchupUrl() {
        return catchupUrl;
    }

    public void setCatchupUrl(String catchupUrl) {
        this.catchupUrl = catchupUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getScreenShotUrl() {
        return screenShotUrl;
    }

    public void setScreenShotUrl(String screenShotUrl) {
        this.screenShotUrl = screenShotUrl;
    }

    public Integer getCatchupDurationTotal() {
        return catchupDurationTotal;
    }

    public void setCatchupDurationTotal(Integer catchupDurationTotal) {
        this.catchupDurationTotal = catchupDurationTotal;
    }

    public ChannelUrlParams getCatchupUrlParams() {
        return catchupURLParams;
    }

    public void setCatchupURLParams(ChannelUrlParams catchupURLParams) {
        this.catchupURLParams = catchupURLParams;
    }

    public RealmList<ChannelTimeShiftStream> getTimeShiftedStreams() {
        return timeShiftedStreams;
    }

    public void setTimeShiftedStreams(RealmList<ChannelTimeShiftStream> timeShiftedStreams) {
        this.timeShiftedStreams = timeShiftedStreams;
    }

    public ParentalRating getParentalRating() {
        return parentalRating;
    }

    public void setParentalRating(ParentalRating parentalRating) {
        this.parentalRating = parentalRating;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public RealmList<ChannelProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(RealmList<ChannelProfile> profiles) {
        this.profiles = profiles;
    }

    public boolean isTrending() {
        return isTrending;
    }

    public void setTrending(boolean trending) {
        isTrending = trending;
    }

    /**
     * Created by: ageech@live.com
     */

    public boolean isPinProtected() {
        return parentalRating != null && getParentalRating().isRequirePin();
    }

    public boolean isNotRented() {
        return !subscribed;
    }

    public int getBestMaxBitrate() {
        int maxBitrate = 0;
        for (ChannelProfile profile : profiles) {
            if (profile.isSubscribed()) {
                if (profile.getMaximumBitrate() > maxBitrate) {
                    maxBitrate = profile.getMaximumBitrate();
                }
            }
        }
        return maxBitrate;
    }

    public boolean isRentProfileAvailable() {
        for (ChannelProfile profile : profiles) {
            if (!profile.isSubscribed()) return true;
        }
        return false;
    }
}
