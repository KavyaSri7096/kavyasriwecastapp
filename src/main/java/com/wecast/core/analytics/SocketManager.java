package com.wecast.core.analytics;

import com.annimon.stream.Stream;
import com.wecast.core.data.db.entities.Banner;
import com.wecast.core.data.db.entities.Channel;
import com.wecast.core.data.db.entities.Vod;
import com.wecast.core.data.db.pref.PreferenceManager;
import com.wecast.core.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by ageech@live.com
 */

public class SocketManager {

    public static final String RECORD_MODEL_CHANNEL = "CHANNEL";
    public static final String RECORD_MODEL_VOD = "SingleEventVod";

    private final PreferenceManager preferenceManager;
    private Socket socket;

    public SocketManager(PreferenceManager preferenceManager) {
        this.preferenceManager = preferenceManager;
    }

    public void connect() {
        try {
            String token = URLEncoder.encode(preferenceManager.getAccessToken(), "UTF-8");
            socket = IO.socket(preferenceManager.getSocketUrl() + "?token=" + token);
            socket.connect();
            Logger.d("Socket connected! -> " + preferenceManager.getSocketUrl() + "?token=" + token);
        } catch (URISyntaxException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        if (socket != null) {
            socket.disconnect();
            Logger.d("Socket disconnected!");
        }
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    /**
     * Socket data for played channels
     */
    private class ChannelPlays {
        private static final String EMIT_CHANNEL_PLAYS = "channel-plays";
        private static final String MODEL_ID = "model_id";
        private static final String MODEL_TYPE = "model_type";
        private static final String RETENTION_TIME = "retention_time";
    }

    public void sendChannelPlayedData(Channel channel, int watched) {
        if (socket != null) {
            socket.emit(ChannelPlays.EMIT_CHANNEL_PLAYS, createChannelPlayedObject(channel, watched));
            Logger.d("channel-plays -> " + channel.getTitle());
        }
    }

    private JSONObject createChannelPlayedObject(Channel channel, int watched) {
        JSONObject object = new JSONObject();
        try {
            object.put(ChannelPlays.MODEL_ID, channel.getId());
            object.put(ChannelPlays.MODEL_TYPE, channel.getType());
            object.put(ChannelPlays.RETENTION_TIME, watched);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Socket data for played videos
     */
    private class VODPlays {
        private static final String EMIT_VOD_PLAYS = "vod-plays";
        private static final String MODEL_ID = "model_id";
        private static final String MODEL_TYPE = "model_type";
        private static final String TYPE = "type";
        private static final String DURATION = "duration";
        private static final String RETENTION_TIME = "retention_time";
        private static final String M_VOD_ID = "m_vod_id";
        private static final String M_SEASON = "m_season";
        private static final String GENRE_IDS = "genre_ids";
        private static final String SHOW_TYPE_IDS = "show_type_ids";
    }

    public void sendVodPlayedData(Vod vod, String profileBusinessModel, long duration, int watched) {
        if (socket != null) {
            socket.emit(VODPlays.EMIT_VOD_PLAYS, createVodPlayedObject(vod, profileBusinessModel, duration, watched));
            Logger.d("vod-plays -> " + vod.getTitle());
        }
    }

    private JSONObject createVodPlayedObject(Vod vod, String profileBusinessModel, long duration, int watched) {
        JSONObject object = new JSONObject();
        try {
            object.put(VODPlays.MODEL_ID, vod.getId());
            object.put(VODPlays.MODEL_TYPE, vod.getType());
            object.put(VODPlays.TYPE, profileBusinessModel);
            object.put(VODPlays.DURATION, duration);
            object.put(VODPlays.RETENTION_TIME, watched);
            object.put(VODPlays.M_VOD_ID, vod.getMultiEventVodId());
            object.put(VODPlays.M_SEASON, vod.getMultiEventVodSeasonId());
            // Genres
            JSONArray genreArray = new JSONArray();
            Stream.of(vod.getGenres()).forEach(vodGenre -> genreArray.put(vodGenre.getId()));
            object.put(VODPlays.GENRE_IDS, genreArray);
            // Show Type
            JSONArray showTypeArray = new JSONArray();
            Stream.of(vod.getShowTypes()).forEach(showType -> showTypeArray.put(showType.getId()));
            object.put(VODPlays.SHOW_TYPE_IDS, showTypeArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Socket data for banner ads
     */
    private class ADSBanner {
        private static final String EMIT_ADS_BANNER = "ads-banner";
        private static final String MONGO_ID = "mongo_id";
        private static final String CLICKED = "clicked";
        private static final String CLOSED = "closed";
    }

    public void sendBannerData(Banner banner, boolean clicked, boolean closed) {
        if (socket != null) {
            socket.emit(ADSBanner.EMIT_ADS_BANNER, createSocketADSBannerObject(banner, clicked, closed));
            Logger.d("ads-banner -> " + banner.getName());
        }
    }

    private JSONObject createSocketADSBannerObject(Banner banner, boolean clicked, boolean closed) {
        JSONObject object = new JSONObject();
        try {
            object.put(ADSBanner.MONGO_ID, banner.getMongoId());
            object.put(ADSBanner.CLICKED, clicked);
            object.put(ADSBanner.CLOSED, closed);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Socket data for video ads
     */
    private class ADSBVideo {
        private static final String EMIT_ADS_VIDEO = "ads-video";
        private static final String MONGO_ID = "mongo_id";
        private static final String CLICKED = "clicked";
        private static final String CLOSED = "closed";
        private static final String DURATION = "duration";
        private static final String RETENTION_TIME = "retention_time";
    }

    /**
     * Socket data for player issues
     */
    private class PlayIssues {
        private static final String EMIT_PLAY_ISSUES = "play-issues";
        private static final String BUFFER = "buffer";
        private static final String CRASH = "crash";
        private static final String B_DURATION = "b_duration";
        private static final String RECORD_MODEL = "record_model";
        private static final String MODEL_ID = "model_id";
        private static final String MODEL_TYPE = "model_type";
    }

    public void sendPlayIssueData(boolean buffer, boolean crash, int duration, String record, int model_id, int model_type) {
        if (socket != null) {
            socket.emit(PlayIssues.EMIT_PLAY_ISSUES, createSocketPlayIssueObject(buffer, crash, duration, record, model_id, model_type));
            Logger.d("play-issues -> " + model_id);
        }
    }

    private JSONObject createSocketPlayIssueObject(boolean buffer, boolean crash, int duration, String record, int model_id, int model_type) {
        JSONObject object = new JSONObject();
        try {
            object.put(PlayIssues.BUFFER, buffer);
            object.put(PlayIssues.CRASH, crash);
            object.put(PlayIssues.B_DURATION, duration);
            object.put(PlayIssues.RECORD_MODEL, record);
            object.put(PlayIssues.MODEL_ID, model_id);
            object.put(PlayIssues.MODEL_TYPE, model_type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Socket data for incidents
     */
    private class Incident {
        private static final String EMIT_INCIDENT = "incident";
        private static final String MESSAGE = "message";
        private static final String CONTROLLER = "controller";
        private static final String ACTION = "action";
        private static final String GET_PARAMS = "get_params";
        private static final String POST_PARAMS = "post_params";
    }

    public void sendIncidentData(String message, String controller, String action, Map<String, String> get, Map<String, String> post) {
        if (socket != null) {
            socket.emit(Incident.EMIT_INCIDENT, createSocketIncidentObject(message, controller, action, get, post));
            Logger.d("incident -> " + controller);
        }
    }

    private JSONObject createSocketIncidentObject(String message, String controller, String action, Map<String, String> get, Map<String, String> post) {
        JSONObject object = new JSONObject();
        try {
            object.put(Incident.MESSAGE, "ANDROID-MOBILE :: " + message);
            object.put(Incident.CONTROLLER, controller);
            object.put(Incident.ACTION, action);
            object.put(Incident.GET_PARAMS, get != null && get.size() > 0 ? createParamsObject(get) : new JSONArray());
            object.put(Incident.POST_PARAMS, post != null && post.size() > 0 ? createParamsObject(post) : new JSONArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    private JSONObject createParamsObject(Map<String, String> params) {
        JSONObject object = new JSONObject();
        Stream.of(params).forEach(param -> {
            try {
                object.put(param.getKey(), param.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        return object;
    }
}
