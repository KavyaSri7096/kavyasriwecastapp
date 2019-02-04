package com.wecast.core.data.api.manager;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.api.service.VodService;
import com.wecast.core.data.db.entities.Rated;
import com.wecast.core.data.db.entities.ShowType;
import com.wecast.core.data.db.entities.TVShow;
import com.wecast.core.data.db.entities.Vod;
import com.wecast.core.data.db.entities.VodGenre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */

public class VodManager {

    private VodService vodService;

    @Inject
    public VodManager(VodService vodService) {
        this.vodService = vodService;
    }

    public Observable<ResponseModel<PagedData<Vod>>> getPage(int page) {
        return vodService.getPage(page, null, null, 1, 1);
    }

    public Observable<ResponseModel<PagedData<Vod>>> getPage(int id, int type) {
        return vodService.getByID(id, type, 1, 1);
    }

    public Observable<ResponseModel<ArrayList<Vod>>> getRecommended() {
        return vodService.getRecommended(null);
    }

    public Observable<ResponseModel<PagedData<Vod>>> getRecentlyAdded(int page) {
        return vodService.getPage(page, "DESC", null, 1, 1);
    }

    public Observable<ResponseModel<ArrayList<Vod>>> getTrending() {
        return vodService.getTrending();
    }

    public Observable<ResponseModel<ArrayList<Vod>>> getContinueWatching() {
        return vodService.getContinueWatching("continue");
    }

    public Observable<ResponseModel<ArrayList<VodGenre>>> getGenres() {
        return vodService.getGenres();
    }

    public Observable<ResponseModel<PagedData<Vod>>> getByID(Integer id, boolean isEpisode) {
        if (isEpisode) {
            return vodService.getByID(id, 1, 1, 1);
        }
        return vodService.getByID(id, null, 1, 1);
    }

    public Observable<ResponseModel<PagedData<Vod>>> getByGenreID(int page, int id) {
        return vodService.getByGenreID(page, id);
    }

    public Observable<ResponseModel<PagedData<Vod>>> search(String query, List<ShowType> filters) {
        Map<String, Integer> queryMap = new HashMap<>();
        if (filters != null && filters.size() > 0) {
            for (int i = 0; i < filters.size(); i++) {
                ShowType showType = filters.get(i);
                queryMap.put("filter[show_type_id][" + i + "]", showType.getId());
            }
        }
        return vodService.search(30, query, queryMap);
    }

    public Observable<ResponseModel<Rated>> rate(int id, int rate, boolean isEpisode) {
        if (isEpisode) {
            return vodService.rateEpisode(id, rate);
        } else {
            return vodService.rateMovie(id, rate);
        }
    }

    public Observable<ResponseModel<Vod>> rent(int id, int profileId, String duration) {
        return vodService.rent(id, profileId, duration);
    }

    public Observable<ResponseModel<Vod>> getSource(Integer id, Integer profileId) {
        return vodService.getSource(id, profileId, 1);
    }

    public Observable<ResponseModel<ArrayList<ShowType>>> getShowTypes() {
        return vodService.getShowTypes();
    }
}
