package com.wecast.core.data.api.service;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.entities.TVGuide;
import com.wecast.core.data.db.entities.TVGuideReminder;

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

public interface TVGuideService {

    @GET("epgs/index")
    Observable<ResponseModel<ArrayList<TVGuide>>> get(@Query("filter[from_timestamp]") String start, @Query("filter[to_timestamp]") String end, @Query("config[enable_empty_data]") Integer enable_empty_data);

    @GET("epgs/list")
    Observable<ResponseModel<PagedData<TVGuide>>> getPage(@Query("page") Integer page, @Query("filter[from_timestamp]") String start, @Query("filter[to_timestamp]") String end, @Query("config[enable_empty_data]") Integer enable_empty_data);

    @GET("epgs/list")
    Observable<ResponseModel<PagedData<TVGuide>>> getPage(@Query("page") Integer page, @Query("filter[id]") Integer id, @Query("filter[from_timestamp]") String start, @Query("filter[to_timestamp]") String end, @Query("config[enable_empty_data]") Integer enable_empty_data);

    @GET("epgs/list")
    Observable<ResponseModel<PagedData<TVGuide>>> getCurrentProgrammes(@Query("page") Integer page, @Query("filter[current]") Integer current, @Query("config[enable_empty_data]") Integer enable_empty_data);

    @GET("epgs/list")
    Observable<ResponseModel<PagedData<TVGuide>>> getCurrentProgrammeByID(@Query("page") Integer page, @Query("filter[current]") Integer current, @Query("config[enable_empty_data]") Integer enable_empty_data, @Query("filter[id]") Integer id);

    @GET("epgs/index")
    Observable<ResponseModel<ArrayList<TVGuide>>> getEpgByID(@Query("filter[epg_channel_id]") Integer id);

    @GET("epg-reminders/index")
    Observable<ResponseModel<ArrayList<TVGuideReminder>>> getReminders();

    @FormUrlEncoded
    @POST("epg-reminders/add")
    Observable<ResponseModel<TVGuideReminder>> addReminder(@Field("channel_id") Integer channel_id, @Field("epg_channel_id") Integer epg_channel_id, @Field("string_id") String epg_programme_string_id, @Field("remind_me") String remind_me);

    @FormUrlEncoded
    @POST("epg-reminders/edit")
    Observable<ResponseModel<TVGuideReminder>> editReminder(@Field("id") Integer id, @Field("remind_me") String remind_me);

    @FormUrlEncoded
    @POST("epg-reminders/delete")
    Observable<ResponseModel> removeReminder(@Field("id") Integer id);
}
