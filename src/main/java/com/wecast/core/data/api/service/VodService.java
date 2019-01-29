package com.wecast.core.data.api.service;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.entities.Rated;
import com.wecast.core.data.db.entities.ShowType;
import com.wecast.core.data.db.entities.Vod;
import com.wecast.core.data.db.entities.VodGenre;

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

public interface VodService {

    @GET("vods/single-event-vods-list")
    Observable<ResponseModel<PagedData<Vod>>> getPage(@Query("page") Integer page, @Query("sort[created]") String sortCreated, @Query("sort[release_date]") String sortReleaseDate, @Query("config[enable_rate]") Integer rate);

    @FormUrlEncoded
    @POST("recommendations/vod-list/viewed")
    Observable<ResponseModel<ArrayList<Vod>>> getRecommended(@Field("type") Integer type);

    @FormUrlEncoded
    @POST("recommendations/vod-list-by-vod-id")
    Observable<ResponseModel<ArrayList<Vod>>> getRecommendedByID(@Field("vod_id") Integer id);

    @GET("vods/trending-movies")
    Observable<ResponseModel<ArrayList<Vod>>> getTrending();

    @GET("vods/get-continue-watching")
    Observable<ResponseModel<ArrayList<Vod>>> getContinueWatching(@Query("filter[type_of_watching]") String cont);

    @GET("vods/genres")
    Observable<ResponseModel<ArrayList<VodGenre>>> getGenres();

    @GET("vods/single-event-vods-list")
    Observable<ResponseModel<PagedData<Vod>>> getByID(@Query("filter[id]") Integer id, @Query("filter[type]") Integer type, @Query("config[enable_rate]") Integer rate);

    @GET("vods/single-event-vods-list")
    Observable<ResponseModel<PagedData<Vod>>> getByGenreID(@Query("page") Integer page, @Query("filter[genre_id]") Integer genreId);

    @GET("vods/single-event-vods-list")
    Observable<ResponseModel<PagedData<Vod>>> search(@Query("limit") Integer limit, @Query("filter[title]") String title, @QueryMap Map<String, Integer> showTypeIds);

    @GET("rate-items/movie-rate/{vod_id}/{rate}")
    Observable<ResponseModel<Rated>> rateMovie(@Path("vod_id") Integer id, @Path("rate") Integer rate);

    @GET("rate-items/episode-rate/{vod_id}/{rate}")
    Observable<ResponseModel<Rated>> rateEpisode(@Path("vod_id") Integer id, @Path("rate") Integer rate);

    @FormUrlEncoded
    @POST("vods/rent")
    Observable<ResponseModel<Vod>> rent(@Field("single_event_vod_id") Integer id, @Field("vod_profile_id") Integer profileId, @Field("duration") String duration);

    @FormUrlEncoded
    @POST("vods/get-movie-source")
    Observable<ResponseModel<Vod>> getSource(@Field("single_event_vod_id") Integer id, @Field("vod_profile_id") Integer profileId, @Query("config[enable_continue_watching]") Integer continueWatching);

    @GET("vods/show-types")
    Observable<ResponseModel<ArrayList<ShowType>>> getShowTypes();
}
