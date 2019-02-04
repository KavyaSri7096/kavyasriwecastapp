package com.wecast.core.data.repository;

import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.manager.VodManager;
import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.VodDao;
import com.wecast.core.data.db.entities.ShowType;
import com.wecast.core.data.db.entities.Vod;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by ageech@live.com
 */

@Singleton
public class VodRepository {

    private final VodDao vodDao;
    private final VodManager vodManager;

    @Inject
    public VodRepository(VodDao vodDao, VodManager vodManager) {
        this.vodDao = vodDao;
        this.vodManager = vodManager;
    }

    /*public Observable<Vod> getByID(int id, boolean isEpisode) {
        return vodManager.getByID(id, isEpisode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(ResponseModel::isSuccessful)
                .map(response -> response.getData().getItems().get(0));
    }*/

    public Observable<Vod> getByID(int id, boolean isEpisode) {
        return Observable.fromCallable(() -> vodDao.getById(id));
    }

    /**
     * Fetch all movies from server and store it to database
     */

    public Observable<ResponseWrapper<List<Vod>>> getAll(boolean forceRemote, int page) {
        Observable<ResponseWrapper<List<Vod>>> data;
        if (forceRemote) {
            data = getAllFromAPI(page);
        } else {
            data = vodDao.getAll().map(ResponseWrapper::success);
        }
        return Observable.concat(getAllFromDB(), data);
    }

    private Observable<ResponseWrapper<List<Vod>>> getAllFromAPI(int page) {
        return vodManager.getPage(page)
                .map((Function<ResponseModel<PagedData<Vod>>, ResponseWrapper<List<Vod>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Vod> data = response.getData().getItems();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Vod> data = response.getData().getItems();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            for (Vod vod : apiResponse.data) {
                                preventFieldOverriding(vod);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<Vod>>> getAllFromDB() {
        return vodDao.getAll()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch recommended movies from server and store it to database
     */

    public Observable<ResponseWrapper<List<Vod>>> getRecommended(boolean forceRemote) {
        Observable<ResponseWrapper<List<Vod>>> data;
        if (forceRemote) {
            data = getRecommendedFromApi();
        } else {
            data = vodDao.getRecommended().map(ResponseWrapper::success);
        }
        return Observable.concat(getRecommendedFromDB(), data);
    }

    private Observable<ResponseWrapper<List<Vod>>> getRecommendedFromApi() {
        return vodManager.getRecommended()
                .map((Function<ResponseModel<ArrayList<Vod>>, ResponseWrapper<List<Vod>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Vod> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Vod> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            // Clear data from database
                            vodDao.clearRecommended();
                            // Insert new data from server
                            for (Vod vod : apiResponse.data) {
                                vod.setRecommended(true);
                                preventFieldOverriding(vod);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<Vod>>> getRecommendedFromDB() {
        return vodDao.getRecommended()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch trending movies from server and store it to database
     */

    public Observable<ResponseWrapper<List<Vod>>> getTrending(boolean forceRemote) {
        Observable<ResponseWrapper<List<Vod>>> data;
        if (forceRemote) {
            data = getTrendingFromApi();
        } else {
            data = vodDao.getTrending().map(ResponseWrapper::success);
        }
        return Observable.concat(getTrendingFromDB(), data);
    }

    private Observable<ResponseWrapper<List<Vod>>> getTrendingFromApi() {
        return vodManager.getTrending()
                .map((Function<ResponseModel<ArrayList<Vod>>, ResponseWrapper<List<Vod>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Vod> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Vod> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            // Clear data from database
                            vodDao.clearTrending();
                            // Insert new data from server
                            for (Vod vod : apiResponse.data) {
                                vod.setTrending(true);
                                preventFieldOverriding(vod);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<Vod>>> getTrendingFromDB() {
        return vodDao.getTrending()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch recently added movies from server and store it to database
     */

    public Observable<ResponseWrapper<List<Vod>>> getRecentlyAdded(boolean forceRemote, int page) {
        Observable<ResponseWrapper<List<Vod>>> data;
        if (forceRemote) {
            data = getRecentlyAddedFromAPI(page);
        } else {
            data = vodDao.getAll().map(ResponseWrapper::success);
        }
        return Observable.concat(getRecentlyAddedFromDB(), data);
    }

    private Observable<ResponseWrapper<List<Vod>>> getRecentlyAddedFromAPI(int page) {
        return vodManager.getRecentlyAdded(page)
                .map((Function<ResponseModel<PagedData<Vod>>, ResponseWrapper<List<Vod>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Vod> data = response.getData().getItems();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Vod> data = response.getData().getItems();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            for (Vod vod : apiResponse.data) {
                                preventFieldOverriding(vod);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<Vod>>> getRecentlyAddedFromDB() {
        return vodDao.getAll()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch all movies by genre ID from server and store it to database
     */

    public Observable<ResponseWrapper<List<Vod>>> getByGenreID(boolean forceRemote, int page, int genreId) {
        Observable<ResponseWrapper<List<Vod>>> data;
        if (forceRemote) {
            data = getByGenreIDFromAPI(page, genreId);
        } else {
            data = vodDao.getByGenreId(genreId).map(ResponseWrapper::success);
        }
        return Observable.concat(getByGenreIDFromDB(genreId), data);
    }

    private Observable<ResponseWrapper<List<Vod>>> getByGenreIDFromAPI(int page, int genreId) {
        return vodManager.getByGenreID(page, genreId)
                .map((Function<ResponseModel<PagedData<Vod>>, ResponseWrapper<List<Vod>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Vod> data = response.getData().getItems();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Vod> data = response.getData().getItems();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            for (Vod vod : apiResponse.data) {
                                preventFieldOverriding(vod);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<Vod>>> getByGenreIDFromDB(int genreId) {
        return vodDao.getByGenreId(genreId)
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch continue watching movies from server and store it to database
     */

    public Observable<ResponseWrapper<List<Vod>>> getContinueWatching(boolean forceRemote) {
        Observable<ResponseWrapper<List<Vod>>> data;
        if (forceRemote) {
            data = getContinueWatchingFromAPI();
        } else {
            data = vodDao.getContinueWatching().map(ResponseWrapper::success);
        }
        return Observable.concat(getContinueWatchingFromDB(), data);
    }

    private Observable<ResponseWrapper<List<Vod>>> getContinueWatchingFromAPI() {
        return vodManager.getContinueWatching()
                .map((Function<ResponseModel<ArrayList<Vod>>, ResponseWrapper<List<Vod>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Vod> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Vod> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            // Clear data from database
                            vodDao.clearContinueWatching();
                            // Insert new data from server
                            for (Vod vod : apiResponse.data) {
                                vod.setContinueWatching(true);
                                preventFieldOverriding(vod);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<Vod>>> getContinueWatchingFromDB() {
        return vodDao.getContinueWatching()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Use this function to prevent field overriding
     * for custom flags
     */
    private void preventFieldOverriding(Vod vod) {
        Vod saved = vodDao.getById(vod.getId());
        if (saved != null) {
            if (saved.isContinueWatching() || vod.isContinueWatching()) {
                vod.setContinueWatching(true);
            }
            if (saved.isRecommended() || vod.isRecommended()) {
                vod.setRecommended(true);
            }
            if (saved.isTrending() || vod.isTrending()) {
                vod.setTrending(true);
            }
        }
        vodDao.insert(vod);
    }

    /**
     * Fetch vod show types
     */

    public Observable<ResponseWrapper<List<ShowType>>> getShowTypes() {
        return vodManager.getShowTypes()
                .map(response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<ShowType> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<ShowType> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                });
    }
}
