package com.wecast.core.data.api.service;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.entities.Rated;
import com.wecast.core.data.db.entities.TVShow;
import com.wecast.core.data.db.entities.TVShowGenre;
import com.wecast.core.data.db.entities.Vod;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ageech@live.com
 */

public interface TVShowService {

    @GET("vods/multi-event-vods-list")
    Observable<ResponseModel<PagedData<TVShow>>> getPage(@Query("page") Integer page, @Query("sort[created]") String sortCreated, @Query("sort[release_date]") String sortReleaseDate, @Query("config[enable_rate]") Integer rate);

    @FormUrlEncoded
    @POST("recommendations/vod-list/viewed")
    Observable<ResponseModel<ArrayList<TVShow>>> getRecommended(@Field("type") Integer type);

    @GET("vods/trending-tv-shows")
    Observable<ResponseModel<ArrayList<TVShow>>> getTrending();

    @GET("vods/genres")
    Observable<ResponseModel<ArrayList<TVShowGenre>>> getGenres();

    @GET("vods/multi-event-vods-list")
    Observable<ResponseModel<PagedData<TVShow>>> getByID(@Query("filter[id]") Integer id, @Query("config[enable_rate]") Integer rate);

    @GET("vods/multi-event-vods-list")
    Observable<ResponseModel<PagedData<TVShow>>> getByGenreID(@Query("page") Integer page, @Query("filter[genre_id]") Integer genreId);

    @GET("vods/single-event-vods-list")
    Observable<ResponseModel<PagedData<Vod>>> getEpisodes(@Query("page") Integer page, @Query("filter[multi_event_vod_id]") Integer tvShowId, @Query("filter[multi_event_vod_season_id]") Integer seasonId, @Query("filter[type]") String type);

    @GET("vods/multi-event-vods-list")
    Observable<ResponseModel<PagedData<TVShow>>> search(@Query("limit") Integer limit, @Query("filter[title]") String title, @QueryMap Map<String, Integer> showTypeIds);

    @GET("rate-items/serie-rate/{tv_show_id}/{rate}")
    Observable<ResponseModel<Rated>> rate(@Path("tv_show_id") Integer id, @Path("rate") Integer rate);
}
