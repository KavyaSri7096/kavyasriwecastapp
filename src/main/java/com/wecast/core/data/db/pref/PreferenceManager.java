package com.wecast.core.data.db.pref;

import com.wecast.core.data.db.entities.AspectRatio;
import com.wecast.core.data.db.entities.Authentication;
import com.wecast.core.data.db.entities.ChannelStreamingProfile;
import com.wecast.core.data.db.entities.Language;

/**
 * Created by ageech@live.com
 */

public interface PreferenceManager {

    void clear();

    void setTheme(int value);

    int getTheme();

    void setLayoutDirection(int value);

    int getLayoutDirection();

    void setAccessToken(String value);

    String getAccessToken();

    void setSocketUrl(String value);

    String getSocketUrl();

    void setAuthentication(Authentication authentication);

    Authentication getAuthentication();

    void setLanguage(Language language);

    Language getLanguage();

    void setVideoQuality(ChannelStreamingProfile streamingProfile);

    ChannelStreamingProfile getVideoQuality();

    void setMaximumBitrate(int bitrate);

    int getMaximumBitrate();

    void setLiveTVBuffer(int buffer);

    int getLiveTVBuffer();

    void setVodBuffer(int buffer);

    int getVodBuffer();

    void setDebug(boolean enabled);

    boolean getDebug();

    void setLastVideoTrack(String track);

    String getLastVideoTrack();

    void setLastAudioTrack(String track);

    String getLastAudioTrack();

    void setLastTextTrack(String track);

    String getLastTextTrack();

    AspectRatio getAspectRatio();

    void setAspectRatio(AspectRatio aspectRatio);

    String getPIN();

    String getPurchasePIN();
}
