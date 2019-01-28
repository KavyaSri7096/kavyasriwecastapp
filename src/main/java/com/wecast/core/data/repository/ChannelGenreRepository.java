package com.wecast.core.data.repository;

import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.manager.ChannelManager;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.ChannelGenreDao;
import com.wecast.core.data.db.entities.ChannelGenre;

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
public class ChannelGenreRepository {

    private final ChannelGenreDao channelGenreDao;
    private final ChannelManager channelManager;

    @Inject
    public ChannelGenreRepository(ChannelGenreDao channelGenreDao, ChannelManager channelManager) {
        this.channelGenreDao = channelGenreDao;
        this.channelManager = channelManager;
    }

    public ChannelGenre getById(int id) {
        return channelGenreDao.getById(id);
    }

    public Observable<ResponseWrapper<List<ChannelGenre>>> getGenres(boolean forceRemote) {
        if (forceRemote) {
            return channelManager.getGenres()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map((Function<ResponseModel<ArrayList<ChannelGenre>>, ResponseWrapper<List<ChannelGenre>>>) response -> {
                        if (response.isTokenExpired()) {
                            return ResponseWrapper.tokenExpired();
                        } else if (response.isSubscriptionExpired()) {
                            List<ChannelGenre> data = response.getData();
                            return ResponseWrapper.subscriptionExpired(data);
                        } else if (response.isSuccessful()) {
                            List<ChannelGenre> data = response.getData();
                            return ResponseWrapper.success(data);
                        } else {
                            return ResponseWrapper.error(response.getMessage());
                        }
                    })
                    .doOnNext(apiResponse -> {
                        if (apiResponse.status == ApiStatus.SUCCESS) {
                            channelGenreDao.insert(apiResponse.data);
                        }
                    });
        } else {
            return channelGenreDao.getAll()
                    .filter(response -> channelGenreDao.getCount() > 0)
                    .map(ResponseWrapper::success);
        }
    }
}
