package com.wecast.core.data.api.service;

import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.entities.composer.Composer;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ageech@live.com
 */

public interface ComposerService {

    @GET("composer/configuration/android")
    Observable<ResponseModel<Composer>> getComposer();
}
