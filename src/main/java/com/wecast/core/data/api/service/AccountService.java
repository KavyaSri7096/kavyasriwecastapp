package com.wecast.core.data.api.service;

import com.wecast.core.data.api.model.ErrorData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.entities.Authentication;
import com.wecast.core.data.db.entities.Subscription;
import com.wecast.core.data.db.entities.Token;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ageech@live.com
 */

public interface AccountService {

    @GET("account/login")
    Observable<ResponseModel<Authentication>> loginWithUsernameAndPassword(
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("account/device-login")
    Observable<ResponseModel<Authentication>> loginWithUID();

    @GET("account/check-device-token")
    Observable<ResponseModel<Token>> checkDeviceToken(
            @Query("token") String token
    );

    @FormUrlEncoded
    @POST("account/forgot-password")
    Observable<ResponseModel> resetPassword(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("account/registration")
    Observable<ResponseModel<ErrorData>> register(
            @Field("username") String username,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("email") String email,
            @Field("password") String password,
            @Field("age") String age,
            @Field("pin") String pin,
            @Field("purchase_pin") String purchasePin,
            @Field("sex") Integer gender,
            @Field("subscription_id") String subscriptionId
    );

    @GET("account/get-subscriptions")
    Observable<ResponseModel<List<Subscription>>> getSubscriptions();

    @GET("account/check-subscription")
    Observable<ResponseModel<Authentication>> checkSubscription();

    @FormUrlEncoded
    @POST("account/update-profile")
    Observable<ResponseModel<ErrorData>> updateInfo(
            @Field("email") String email,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("password") String password,
            @Field("confirm_password") String confirm_password,
            @Field("purchase_pin") String purchase_pin,
            @Field("pin") String pin
    );

    @GET("account/logout")
    Observable<ResponseModel> logout();
}
