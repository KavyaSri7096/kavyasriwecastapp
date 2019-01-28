package com.wecast.core.data.api.service;

import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.entities.Highlighted;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ageech@live.com
 */

public interface HighlightedService {

    @GET("highlight-contents")
    Observable<ResponseModel<ArrayList<Highlighted>>> getAll();
}
