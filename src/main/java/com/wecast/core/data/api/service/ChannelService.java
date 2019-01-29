package com.wecast.core.data.api.service;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.entities.Channel;
import com.wecast.core.data.db.entities.ChannelGenre;
import com.wecast.core.data.db.entities.ChannelStreamingProfile;
import com.wecast.core.data.db.entities.Favorite;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ageech@live.com
 */

public interface ChannelService {

    @GET("channels")
    Observable<ResponseModel<ArrayList<Channel>>> getAll(
            @Query(value = "filter[category_id]", encoded = true) Integer genreId,
            @Query(value = "filter[title]", encoded = true) String title,
            @Query(value = "config[enable_favorites]", encoded = true) Integer favorite
    );

    @GET("channels/list")
    Observable<ResponseModel<PagedData<Channel>>> getPage(
            @Query("page") Integer page,
            @Query(value = "config[enable_favorites]", encoded = true) Integer favorite
    );

    @GET("channels/favorites")
    Observable<ResponseModel<ArrayList<Channel>>> getFavorites(
            @Query(value = "config[enable_favorites]", encoded = true) Integer favorite
    );

    @GET("channels/trendings")
    Observable<ResponseModel<ArrayList<Channel>>> getTrending(
            @Query(value = "config[enable_favorites]", encoded = true) Integer favorite
    );

    @GET("channels/categories")
    Observable<ResponseModel<ArrayList<ChannelGenre>>> getGenres();

    @GET("channels")
    Observable<ResponseModel<ArrayList<Channel>>> getByID(
            @Query(value = "filter[id]", encoded = true) Integer id,
            @Query(value = "config[enable_favorites]", encoded = true) Integer favorite
    );

    @GET("channels")
    Observable<ResponseModel<ArrayList<Channel>>> getByGenreID(
            @Query(value = "filter[category_id]", encoded = true) Integer id,
            @Query(value = "config[enable_favorites]", encoded = true) Integer favorite
    );

    @GET("channels")
    Observable<ResponseModel<ArrayList<Channel>>> search(
            @Query(value = "filter[title]", encoded = true) String title,
            @Query(value = "config[enable_favorites]", encoded = true) Integer favorite,
            @QueryMap(encoded = true) Map<String, Integer> channelGenreIds
    );

    @FormUrlEncoded
    @POST("channels/rent")
    Observable<ResponseModel<Channel>> rent(
            @Field("channel_id") Integer channelId,
            @Field("streaming_profile_id") Integer streamingProfileId
    );

    @FormUrlEncoded
    @POST("channels/add-to-favorites")
    Observable<ResponseModel<Favorite>> addToFavorites(
            @Field("channel_id") Integer id
    );

    @FormUrlEncoded
    @POST("channels/delete-from-favorites")
    Observable<ResponseModel<Favorite>> removeFromFavorites(
            @Field("channel_id") Integer id
    );

    @GET("channels/available-profiles")
    Observable<ResponseModel<ArrayList<ChannelStreamingProfile>>> getStreamingProfiles();
}
