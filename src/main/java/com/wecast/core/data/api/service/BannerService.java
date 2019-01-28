package com.wecast.core.data.api.service;

import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.entities.Banner;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ageech@live.com
 */

public interface BannerService {

    @GET("ads/active-banner-campaigns")
    Observable<ResponseModel<Banner>> getBanner(@Query("filter[box_position]") String position);
}
