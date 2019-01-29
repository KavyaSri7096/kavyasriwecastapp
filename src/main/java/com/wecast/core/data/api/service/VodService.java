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
    Observable<ResponseModel<PagedData<Vod>>> getPage(
            @Query("page") Integer page,
            @Query(value = "sort[created]", encoded = true) String sortCreated,
            @Query(value = "sort[release_date]", encoded = true) String sortReleaseDate,
            @Query(value = "config[enable_rate]", encoded = true) Integer rate
    );

    @FormUrlEncoded
    @POST("recommendations/vod-list/viewed")
    Observable<ResponseModel<ArrayList<Vod>>> getRecommended(
            @Field("type") Integer type
    );

    @FormUrlEncoded
    @POST("recommendations/vod-list-by-vod-id")
    Observable<ResponseModel<ArrayList<Vod>>> getRecommendedByID(
            @Field("vod_id") Integer id
    );

    @GET("vods/trending-movies")
    Observable<ResponseModel<ArrayList<Vod>>> getTrending();

    @GET("vods/get-continue-watching")
    Observable<ResponseModel<ArrayList<Vod>>> getContinueWatching(
            @Query(value = "filter[type_of_watching]", encoded = true) String cont
    );

    @GET("vods/genres")
    Observable<ResponseModel<ArrayList<VodGenre>>> getGenres();

    @GET("vods/single-event-vods-list")
    Observable<ResponseModel<PagedData<Vod>>> getByID(
            @Query(value = "filter[id]", encoded = true) Integer id,
            @Query(value = "filter[type]", encoded = true) Integer type,
            @Query(value = "config[enable_rate]", encoded = true) Integer rate
    );

    @GET("vods/single-event-vods-list")
    Observable<ResponseModel<PagedData<Vod>>> getByGenreID(
            @Query("page") Integer page,
            @Query(value = "filter[genre_id]", encoded = true) Integer genreId
    );

    @GET("vods/single-event-vods-list")
    Observable<ResponseModel<PagedData<Vod>>> search(
            @Query("limit") Integer limit,
            @Query(value = "filter[title]", encoded = true) String title,
            @QueryMap Map<String, Integer> showTypeIds
    );

    @GET("rate-items/movie-rate/{vod_id}/{rate}")
    Observable<ResponseModel<Rated>> rateMovie(
            @Path("vod_id") Integer id,
            @Path("rate") Integer rate
    );

    @GET("rate-items/episode-rate/{vod_id}/{rate}")
    Observable<ResponseModel<Rated>> rateEpisode(
            @Path("vod_id") Integer id,
            @Path("rate") Integer rate
    );

    @FormUrlEncoded
    @POST("vods/rent")
    Observable<ResponseModel<Vod>> rent(
            @Field("single_event_vod_id") Integer id,
            @Field("vod_profile_id") Integer profileId,
            @Field("duration") String duration
    );

    @FormUrlEncoded
    @POST("vods/get-movie-source")
    Observable<ResponseModel<Vod>> getSource(
            @Field("single_event_vod_id") Integer id,
            @Field("vod_profile_id") Integer profileId,
            @Query(value = "config[enable_continue_watching]", encoded = true) Integer continueWatching
    );

    @GET("vods/show-types")
    Observable<ResponseModel<ArrayList<ShowType>>> getShowTypes();
}
