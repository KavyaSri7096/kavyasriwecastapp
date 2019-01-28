package com.wecast.core.data.api.service;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.entities.Channel;
import com.wecast.core.data.db.entities.ChannelGenre;
import com.wecast.core.data.db.entities.ChannelStreamingProfile;
import com.wecast.core.data.db.entities.Favorite;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ageech@live.com
 */

public interface ChannelService {

    @GET("channels")
    Observable<ResponseModel<ArrayList<Channel>>> getAll(@Query("filter[category_id]") Integer genreId, @Query("filter[title]") String title, @Query("config[enable_favorites]") Integer favorite);

    @GET("channels/list")
    Observable<ResponseModel<PagedData<Channel>>> getPage(@Query("page") Integer page, @Query("config[enable_favorites]") Integer favorite);

    @GET("channels/favorites")
    Observable<ResponseModel<ArrayList<Channel>>> getFavorites(@Query("config[enable_favorites]") Integer favorite);

    @GET("channels/trendings")
    Observable<ResponseModel<ArrayList<Channel>>> getTrending(@Query("config[enable_favorites]") Integer favorite);

    @GET("channels/categories")
    Observable<ResponseModel<ArrayList<ChannelGenre>>> getCategories();

    @GET("channels")
    Observable<ResponseModel<ArrayList<Channel>>> getByID(@Query("filter[id]") Integer id, @Query("config[enable_favorites]") Integer favorite);

    @GET("channels")
    Observable<ResponseModel<ArrayList<Channel>>> getByGenreID(@Query("filter[category_id]") Integer id, @Query("config[enable_favorites]") Integer favorite);

    @FormUrlEncoded
    @POST("channels/rent")
    Observable<ResponseModel<Channel>> rent(@Field("channel_id") Integer channel_id, @Field("streaming_profile_id") Integer streaming_profile_id);

    @FormUrlEncoded
    @POST("channels/add-to-favorites")
    Observable<ResponseModel<Favorite>> addToFavorites(@Field("channel_id") Integer id);

    @FormUrlEncoded
    @POST("channels/delete-from-favorites")
    Observable<ResponseModel<Favorite>> removeFromFavorites(@Field("channel_id") Integer id);

    @GET("channels/available-profiles")
    Observable<ResponseModel<ArrayList<ChannelStreamingProfile>>> getStreamingProfiles();
}
