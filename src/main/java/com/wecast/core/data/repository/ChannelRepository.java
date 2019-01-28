package com.wecast.core.data.repository;

import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.manager.ChannelManager;
import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.ChannelDao;
import com.wecast.core.data.db.entities.Channel;

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
public class ChannelRepository {

    private final ChannelDao channelDao;
    private final ChannelManager channelManager;

    @Inject
    public ChannelRepository(ChannelDao channelDao, ChannelManager channelManager) {
        this.channelDao = channelDao;
        this.channelManager = channelManager;
    }

    public Observable<Channel> getById(int id) {
        return channelManager.getById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(ResponseModel::isSuccessful)
                .map(response -> response.getData().get(0));
    }

    /**
     * Fetch all channels from server and store it to database
     */

    public Observable<ResponseWrapper<List<Channel>>> getAll() {
        return channelManager.getAll()
                .map((Function<ResponseModel<ArrayList<Channel>>, ResponseWrapper<List<Channel>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Channel> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Channel> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        channelDao.insert(apiResponse.data);
                    }
                });
    }

    public Observable<ResponseWrapper<List<Channel>>> getAll(boolean forceRemote, int page) {
        Observable<ResponseWrapper<List<Channel>>> data;
        if (forceRemote) {
            data = getAllFromAPI(page);
        } else {
            data = channelDao.getAll().map(ResponseWrapper::success);
        }
        return Observable.concat(getAllFromDB(), data);
    }

    private Observable<ResponseWrapper<List<Channel>>> getAllFromAPI(int page) {
        return channelManager.getPage(page)
                .map((Function<ResponseModel<PagedData<Channel>>, ResponseWrapper<List<Channel>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Channel> data = response.getData().getItems();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Channel> data = response.getData().getItems();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        channelDao.insert(apiResponse.data);
                    }
                });
    }

    private Observable<ResponseWrapper<List<Channel>>> getAllFromDB() {
        return channelDao.getAll()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch favorite channels from server and store it to database
     */

    public Observable<ResponseWrapper<List<Channel>>> getFavorites(boolean forceRemote) {
        Observable<ResponseWrapper<List<Channel>>> data;
        if (forceRemote) {
            data = getFavoritesFromApi();
        } else {
            data = channelDao.getFavorites().map(ResponseWrapper::success);
        }
        return Observable.concat(getFavoritesFromDB(), data);
    }

    private Observable<ResponseWrapper<List<Channel>>> getFavoritesFromApi() {
        return channelManager.getFavorites()
                .map((Function<ResponseModel<ArrayList<Channel>>, ResponseWrapper<List<Channel>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Channel> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Channel> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        channelDao.insert(apiResponse.data);
                    }
                });
    }

    private Observable<ResponseWrapper<List<Channel>>> getFavoritesFromDB() {
        return channelDao.getFavorites()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch trending channels from server and store it to database
     */

    public Observable<ResponseWrapper<List<Channel>>> getTrending(boolean forceRemote) {
        Observable<ResponseWrapper<List<Channel>>> data;
        if (forceRemote) {
            data = getTrendingFromApi();
        } else {
            data = channelDao.getTrending().map(ResponseWrapper::success);
        }
        return Observable.concat(getTrendingFromDB(), data);
    }

    private Observable<ResponseWrapper<List<Channel>>> getTrendingFromApi() {
        return channelManager.getTrending()
                .map((Function<ResponseModel<ArrayList<Channel>>, ResponseWrapper<List<Channel>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Channel> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Channel> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        channelDao.insert(apiResponse.data);
                    }
                });
    }

    private Observable<ResponseWrapper<List<Channel>>> getTrendingFromDB() {
        return channelDao.getTrending()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch channels by genre id from server and store it to database
     */

    public Observable<ResponseWrapper<List<Channel>>> getByGenreId(boolean forceRemote, int genreId) {
        Observable<ResponseWrapper<List<Channel>>> data;
        if (forceRemote) {
            data = getByGenreIdFromApi(genreId);
        } else {
            data = channelDao.getByGenreId(genreId).map(ResponseWrapper::success);
        }
        return Observable.concat(getByGenreIdFromDB(genreId), data);
    }

    private Observable<ResponseWrapper<List<Channel>>> getByGenreIdFromApi(int genreId) {
        return channelManager.getByGenreId(genreId)
                .map((Function<ResponseModel<ArrayList<Channel>>, ResponseWrapper<List<Channel>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Channel> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Channel> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        channelDao.insert(apiResponse.data);
                    }
                });
    }

    private Observable<ResponseWrapper<List<Channel>>> getByGenreIdFromDB(int genreId) {
        return channelDao.getByGenreId(genreId)
                .map(ResponseWrapper::loading)
                .take(1);
    }
}
