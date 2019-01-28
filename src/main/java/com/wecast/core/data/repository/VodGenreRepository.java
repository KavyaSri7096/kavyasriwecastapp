package com.wecast.core.data.repository;

import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.manager.VodManager;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.VodGenreDao;
import com.wecast.core.data.db.entities.VodGenre;

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
public class VodGenreRepository {

    private final VodGenreDao vodGenreDao;
    private final VodManager vodManager;

    @Inject
    public VodGenreRepository(VodGenreDao vodGenreDao, VodManager vodManager) {
        this.vodGenreDao = vodGenreDao;
        this.vodManager = vodManager;
    }

    public Observable<ResponseWrapper<List<VodGenre>>> getGenres(boolean forceRemote) {
        if (forceRemote) {
            return vodManager.getGenres()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map((Function<ResponseModel<ArrayList<VodGenre>>, ResponseWrapper<List<VodGenre>>>) response -> {
                        if (response.isTokenExpired()) {
                            return ResponseWrapper.tokenExpired();
                        } else if (response.isSubscriptionExpired()) {
                            List<VodGenre> data = response.getData();
                            return ResponseWrapper.subscriptionExpired(data);
                        } else if (response.isSuccessful()) {
                            List<VodGenre> data = response.getData();
                            return ResponseWrapper.success(data);
                        } else {
                            return ResponseWrapper.error(response.getMessage());
                        }
                    })
                    .doOnNext(apiResponse -> {
                        if (apiResponse.status == ApiStatus.SUCCESS) {
                            vodGenreDao.insert(apiResponse.data);
                        }
                    });
        } else {
            return vodGenreDao.getAll()
                    .filter(response -> vodGenreDao.getCount() > 0)
                    .map(ResponseWrapper::success);
        }
    }
}
