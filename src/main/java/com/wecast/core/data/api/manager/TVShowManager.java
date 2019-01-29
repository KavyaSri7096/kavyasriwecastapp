package com.wecast.core.data.api.manager;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.api.service.TVShowService;
import com.wecast.core.data.db.entities.Rated;
import com.wecast.core.data.db.entities.ShowType;
import com.wecast.core.data.db.entities.TVShow;
import com.wecast.core.data.db.entities.TVShowGenre;
import com.wecast.core.data.db.entities.Vod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */


public class TVShowManager {

    private TVShowService tvShowService;

    @Inject
    public TVShowManager(TVShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    public Observable<ResponseModel<PagedData<TVShow>>> getPage(int page) {
        return tvShowService.getPage(page, null, null, 1);
    }

    public Observable<ResponseModel<ArrayList<TVShow>>> getRecommended() {
        return tvShowService.getRecommended(1);
    }

    public Observable<ResponseModel<ArrayList<TVShow>>> getTrending() {
        return tvShowService.getTrending();
    }

    public Observable<ResponseModel<PagedData<TVShow>>> getRecentlyAdded(int page) {
        return tvShowService.getPage(page, "DESC", null, 1);
    }

    public Observable<ResponseModel<ArrayList<TVShowGenre>>> getGenres() {
        return tvShowService.getGenres();
    }

    public Observable<ResponseModel<PagedData<TVShow>>> getByID(int id) {
        return tvShowService.getByID(id, 1);
    }

    public Observable<ResponseModel<PagedData<TVShow>>> getByGenreID(int page, int id) {
        return tvShowService.getByGenreID(page, id);
    }

    public Observable<ResponseModel<PagedData<Vod>>> getEpisodes(int page, int tvShowId, int seasonId) {
        return tvShowService.getEpisodes(page, tvShowId, seasonId, "1");
    }

    public Observable<ResponseModel<PagedData<TVShow>>> search(String query, List<ShowType> showTypeList) {
        if (showTypeList != null && showTypeList.size() > 0) {
            Map<String, Integer> queryMap = new HashMap<>();
            for (int i = 0; i < showTypeList.size(); i++) {
                ShowType showType = showTypeList.get(i);
                queryMap.put("filter[show_type_id][" + i + "]=", showType.getId());
            }
            return tvShowService.search(30, query, queryMap);
        }
        return tvShowService.search(30, query, new HashMap<>());
    }

    public Observable<ResponseModel<Rated>> rate(int tvShowId, int rate) {
        return tvShowService.rate(tvShowId, rate);
    }
}
