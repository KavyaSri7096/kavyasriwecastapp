package com.wecast.core.data.db.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.google.gson.Gson;
import com.wecast.core.Constants;
import com.wecast.core.data.db.entities.AspectRatio;
import com.wecast.core.data.db.entities.Authentication;
import com.wecast.core.data.db.entities.ChannelStreamingProfile;
import com.wecast.core.data.db.entities.Language;
import com.wecast.core.di.PreferenceInfo;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ageech@live.com
 */

public class AppPreferenceManager implements PreferenceManager {

    private static final String PREF_KEY_THEME = "PREF_KEY_THEME";
    private static final String PREF_KEY_LAYOUT_DIRECTION = "PREF_KEY_LAYOUT_DIRECTION";
    private static final String PREF_KEY_AUTHENTICATION = "PREF_KEY_AUTHENTICATION";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_SOCKET_URL = "PREF_KEY_SOCKET_URL";
    private static final String PREF_KEY_LANGUAGE = "PREF_KEY_LANGUAGE";
    private static final String PREF_KEY_VIDEO_QUALITY = "PREF_KEY_VIDEO_QUALITY";
    private static final String PREF_KEY_MAX_BITRATE = "PREF_KEY_MAX_BITRATE";
    private static final String PREF_KEY_LIVE_TV_BUFFER = "PREF_KEY_LIVE_TV_BUFFER";
    private static final String PREF_KEY_VOD_BUFFER = "PREF_KEY_VOD_BUFFER";
    private static final String PREF_KEY_DEBUG = "PREF_KEY_DEBUG";
    private static final String PREF_KEY_LAST_VIDEO_TRACK = "PREF_KEY_LAST_VIDEO_TRACK";
    private static final String PREF_KEY_LAST_AUDIO_TRACK = "PREF_KEY_LAST_AUDIO_TRACK";
    private static final String PREF_KEY_LAST_TEXT_TRACK = "PREF_KEY_LAST_TEXT_TRACK";
    private static final String PREF_KEY_ASPECT_RATIO = "PREF_KEY_ASPECT_RATIO";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Inject
    AppPreferenceManager(Context context, Gson gson, @PreferenceInfo String prefFileName) {
        this.sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        this.gson = gson;
    }

    @Override
    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Map<String, ?> prefs = sharedPreferences.getAll();
        for (Map.Entry<String, ?> prefToReset : prefs.entrySet()) {
            if (!prefToReset.getKey().equals(PREF_KEY_SOCKET_URL)) {
                editor.remove(prefToReset.getKey());
            }
        }
        editor.apply();
    }

    @Override
    public void setTheme(int value) {
        sharedPreferences.edit().putInt(PREF_KEY_THEME, value).apply();
    }

    @Override
    public int getTheme() {
        return sharedPreferences.getInt(PREF_KEY_THEME, Constants.DEFAULT_THEME);
    }

    @Override
    public void setLayoutDirection(int value) {
        sharedPreferences.edit().putInt(PREF_KEY_LAYOUT_DIRECTION, value).apply();
    }

    @Override
    public int getLayoutDirection() {
        return sharedPreferences.getInt(PREF_KEY_LAYOUT_DIRECTION, View.LAYOUT_DIRECTION_LTR);
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        String auth = gson.toJson(authentication, Authentication.class);
        sharedPreferences.edit().putString(PREF_KEY_AUTHENTICATION, auth).apply();
    }

    @Override
    public Authentication getAuthentication() {
        String jsonString = sharedPreferences.getString(PREF_KEY_AUTHENTICATION, null);
        return jsonString != null ? gson.fromJson(jsonString, Authentication.class) : null;
    }

    @Override
    public void setAccessToken(String value) {
        sharedPreferences.edit().putString(PREF_KEY_ACCESS_TOKEN, value).apply();
    }

    @Override
    public String getAccessToken() {
        return sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setSocketUrl(String value) {
        sharedPreferences.edit().putString(PREF_KEY_SOCKET_URL, value).apply();
    }

    @Override
    public String getSocketUrl() {
        return sharedPreferences.getString(PREF_KEY_SOCKET_URL, null);
    }

    @Override
    public void setLanguage(Language language) {
        String lang = gson.toJson(language, Language.class);
        sharedPreferences.edit().putString(PREF_KEY_LANGUAGE, lang).apply();
    }

    @Override
    public Language getLanguage() {
        String jsonString = sharedPreferences.getString(PREF_KEY_LANGUAGE, null);
        Language defaultLang = new Language(1, "English", "en");
        return jsonString != null ? gson.fromJson(jsonString, Language.class) : defaultLang;
    }

    @Override
    public void setVideoQuality(ChannelStreamingProfile streamingProfile) {
        String profile = gson.toJson(streamingProfile, ChannelStreamingProfile.class);
        sharedPreferences.edit().putString(PREF_KEY_VIDEO_QUALITY, profile).apply();
    }

    @Override
    public ChannelStreamingProfile getVideoQuality() {
        String jsonString = sharedPreferences.getString(PREF_KEY_VIDEO_QUALITY, null);
        ChannelStreamingProfile defaultProfile = new ChannelStreamingProfile(0, "Auto", -1, -1);
        return jsonString != null ? gson.fromJson(jsonString, ChannelStreamingProfile.class) : defaultProfile;
    }

    @Override
    public void setMaximumBitrate(int bitrate) {
        sharedPreferences.edit().putInt(PREF_KEY_MAX_BITRATE, bitrate).apply();
    }

    @Override
    public int getMaximumBitrate() {
        return sharedPreferences.getInt(PREF_KEY_MAX_BITRATE, -1);
    }

    @Override
    public void setLiveTVBuffer(int buffer) {
        sharedPreferences.edit().putInt(PREF_KEY_LIVE_TV_BUFFER, buffer).apply();
    }

    @Override
    public int getLiveTVBuffer() {
        return sharedPreferences.getInt(PREF_KEY_LIVE_TV_BUFFER, 10);
    }

    @Override
    public void setVodBuffer(int buffer) {
        sharedPreferences.edit().putInt(PREF_KEY_VOD_BUFFER, buffer).apply();
    }

    @Override
    public int getVodBuffer() {
        return sharedPreferences.getInt(PREF_KEY_VOD_BUFFER, 10);
    }

    @Override
    public boolean getDebug() {
        return sharedPreferences.getBoolean(PREF_KEY_DEBUG, false);
    }

    @Override
    public void setDebug(boolean enabled) {
        sharedPreferences.edit().putBoolean(PREF_KEY_DEBUG, enabled).apply();
    }

    @Override
    public void setLastVideoTrack(String track) {
        sharedPreferences.edit().putString(PREF_KEY_LAST_VIDEO_TRACK, track).apply();
    }

    @Override
    public String getLastVideoTrack() {
        return sharedPreferences.getString(PREF_KEY_LAST_VIDEO_TRACK, "");
    }

    @Override
    public void setLastAudioTrack(String track) {
        sharedPreferences.edit().putString(PREF_KEY_LAST_AUDIO_TRACK, track).apply();
    }

    @Override
    public String getLastAudioTrack() {
        return sharedPreferences.getString(PREF_KEY_LAST_AUDIO_TRACK, "");
    }

    @Override
    public void setLastTextTrack(String track) {
        sharedPreferences.edit().putString(PREF_KEY_LAST_TEXT_TRACK, track).apply();
    }

    @Override
    public String getLastTextTrack() {
        return sharedPreferences.getString(PREF_KEY_LAST_TEXT_TRACK, "");
    }

    @Override
    public AspectRatio getAspectRatio() {
        String jsonString = sharedPreferences.getString(PREF_KEY_ASPECT_RATIO, null);
        AspectRatio aspectRatio = new AspectRatio(
                AspectRatio.ASPECT_RATIO_ORIGINAL,
                AspectRatio.RATIO_ORIGINAL,
                AspectRatio.RATIO_MULTIPLIER_ORIGINAL
        );
        return jsonString != null ? gson.fromJson(jsonString, AspectRatio.class) : aspectRatio;
    }

    @Override
    public void setAspectRatio(AspectRatio aspectRatio) {
        String profile = gson.toJson(aspectRatio, AspectRatio.class);
        sharedPreferences.edit().putString(PREF_KEY_ASPECT_RATIO, profile).apply();
    }

    @Override
    public String getPIN() {
        return getAuthentication().getProfile().getPin();
    }

    @Override
    public String getPurchasePIN() {
        return getAuthentication().getAccount().getPurchasePin();
    }

    @Override
    public boolean is24hTimeFormat() {
        return false;
    }

    @Override
    public void set24hTimeFormat(boolean checked) {

    }
}
