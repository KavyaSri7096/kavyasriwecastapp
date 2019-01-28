package com.wecast.core.data.api.manager;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.api.service.ChannelService;
import com.wecast.core.data.db.entities.Channel;
import com.wecast.core.data.db.entities.ChannelGenre;
import com.wecast.core.data.db.entities.ChannelStreamingProfile;
import com.wecast.core.data.db.entities.Favorite;

import java.util.ArrayList;

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
        return channelService.getAll(null, null,1);
    }

    public Observable<ResponseModel<PagedData<Channel>>> getPage(int page) {
        return channelService.getPage(page, 1);
    }

    public Observable<ResponseModel<ArrayList<Channel>>> getFavorites() {
        return channelService.getFavorites(1);
    }

    public Observable<ResponseModel<ArrayList<Channel>>> getTrending() {
        return channelService.getTrending(1);
    }

    public Observable<ResponseModel<ArrayList<ChannelGenre>>> getGenres() {
        return channelService.getCategories();
    }

    public Observable<ResponseModel<ArrayList<Channel>>> getById(int id) {
        return channelService.getByID(id, 1);
    }

    public Observable<ResponseModel<ArrayList<Channel>>> getByGenreId(int id) {
        return channelService.getByGenreID(id, 1);
    }

    public Observable<ResponseModel<ArrayList<Channel>>> search(String query) {
        return channelService.getAll(null, query, 1);
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