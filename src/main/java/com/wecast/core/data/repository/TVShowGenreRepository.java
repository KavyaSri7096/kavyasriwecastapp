package com.wecast.core.data.repository;

import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.manager.TVShowManager;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.TVShowGenreDao;
import com.wecast.core.data.db.entities.TVShowGenre;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ageech@live.com
 */

@Singleton
public class TVShowGenreRepository {

    private final TVShowGenreDao tvShowGenreDao;
    private final TVShowManager tvShowManager;

    @Inject
    public TVShowGenreRepository(TVShowGenreDao tvShowGenreDao, TVShowManager tvShowManager) {
        this.tvShowGenreDao = tvShowGenreDao;
        this.tvShowManager = tvShowManager;
    }

    public Observable<ResponseWrapper<List<TVShowGenre>>> getGenres(boolean forceRemote) {
        if (forceRemote) {
            return tvShowManager.getGenres()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map((Function<ResponseModel<ArrayList<TVShowGenre>>, ResponseWrapper<List<TVShowGenre>>>) response -> {
                        if (response.isTokenExpired()) {
                            return ResponseWrapper.tokenExpired();
                        } else if (response.isSubscriptionExpired()) {
                            List<TVShowGenre> data = response.getData();
                            return ResponseWrapper.subscriptionExpired(data);
                        } else if (response.isSuccessful()) {
                            List<TVShowGenre> data = response.getData();
                            return ResponseWrapper.success(data);
                        } else {
                            return ResponseWrapper.error(response.getMessage());
                        }
                    })
                    .doOnNext(apiResponse -> {
                        if (apiResponse.status == ApiStatus.SUCCESS) {
                            tvShowGenreDao.insert(apiResponse.data);
                        }
                    });
        } else {
            return tvShowGenreDao.getAll()
                    .filter(response -> tvShowGenreDao.getCount() > 0)
                    .map(ResponseWrapper::success);
        }
    }
}
