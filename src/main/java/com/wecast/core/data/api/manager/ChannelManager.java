package com.wecast.core.data.api.manager;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.api.service.ChannelService;
import com.wecast.core.data.db.entities.Channel;
import com.wecast.core.data.db.entities.ChannelGenre;
import com.wecast.core.data.db.entities.ChannelStreamingProfile;
import com.wecast.core.data.db.entities.Favorite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */

public class ChannelManager {

    private ChannelService channelService;

    @Inject
    public ChannelManager(ChannelService channelService) {
        this.channelService = channelService;
    }

    public Observable<ResponseModel<ArrayList<Channel>>> getAll() {
        return channelService.getAll(null, null, 1);
    }

    public Observable<ResponseModel<PagedData<Channel>>> getPage(int page) {
        return channelService.getPage(page, 1);
    }

    public Observable<ResponseModel<ArrayList<Channel>>> getFavorites() {
        return channelService.getFavorites(1);
    }

    public Observable<ResponseModel<ArrayList<Channel>>> getTrending() {
        return channelService.getTrending(1, 10);
    }

    public Observable<ResponseModel<ArrayList<ChannelGenre>>> getGenres() {
        return channelService.getGenres();
    }

    public Observable<ResponseModel<ArrayList<Channel>>> getById(int id) {
        return channelService.getByID(id, 1);
    }

    public Observable<ResponseModel<ArrayList<Channel>>> getByGenreId(int id) {
        return channelService.getByGenreID(id, 1);
    }

    public Observable<ResponseModel<PagedData<Channel>>> search(String query, List<ChannelGenre> filters) {
        Map<String, Integer> queryMap = new HashMap<>();
        if (filters != null && filters.size() > 0) {
            for (int i = 0; i < filters.size(); i++) {
                ChannelGenre channelGenre = filters.get(i);
                queryMap.put("filter[category_id][" + i + "]", channelGenre.getId());
            }
        }
        return channelService.search(query, 1, queryMap);
    }

    public Observable<ResponseModel<Channel>> rent(int id, int profileId) {
        return channelService.rent(id, profileId);
    }

    public Observable<ResponseModel<Favorite>> addToFavorites(Integer id) {
        return channelService.addToFavorites(id);
    }

    public Observable<ResponseModel<Favorite>> removeFromFavorites(Integer id) {
        return channelService.removeFromFavorites(id);
    }

    public Observable<ResponseModel<ArrayList<ChannelStreamingProfile>>> getStreamingProfiles() {
        return channelService.getStreamingProfiles();
    }
}
