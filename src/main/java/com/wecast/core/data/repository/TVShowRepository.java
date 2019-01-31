package com.wecast.core.data.repository;

import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.manager.TVShowManager;
import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.TVShowDao;
import com.wecast.core.data.db.dao.VodDao;
import com.wecast.core.data.db.entities.TVShow;
import com.wecast.core.data.db.entities.Vod;

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
public class TVShowRepository {

    private final TVShowDao tvShowDao;
    private final TVShowManager tvShowManager;
    private final VodDao vodDao;

    @Inject
    public TVShowRepository(TVShowDao tvShowDao, TVShowManager tvShowManager, VodDao vodDao) {
        this.tvShowDao = tvShowDao;
        this.tvShowManager = tvShowManager;
        this.vodDao = vodDao;
    }

    public Observable<TVShow> getByID(int id) {
        return tvShowManager.getByID(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(ResponseModel::isSuccessful)
                .map(response -> response.getData().getItems().get(0));
    }

    /**
     * Fetch all tv shows from server and store it to database
     */

    public Observable<ResponseWrapper<List<TVShow>>> getAll(boolean forceRemote, int page) {
        Observable<ResponseWrapper<List<TVShow>>> data;
        if (forceRemote) {
            data = getAllFromAPI(page);
        } else {
            data = tvShowDao.getAll().map(ResponseWrapper::success);
        }
        return Observable.concat(getAllFromDB(), data);
    }

    private Observable<ResponseWrapper<List<TVShow>>> getAllFromAPI(int page) {
        return tvShowManager.getPage(page)
                .map((Function<ResponseModel<PagedData<TVShow>>, ResponseWrapper<List<TVShow>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<TVShow> data = response.getData().getItems();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<TVShow> data = response.getData().getItems();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            for (TVShow tvShow : apiResponse.data) {
                                preventFieldOverriding(tvShow);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<TVShow>>> getAllFromDB() {
        return tvShowDao.getAll()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch recommended tv shows from server and store it to database
     */

    public Observable<ResponseWrapper<List<TVShow>>> getRecommended(boolean forceRemote) {
        Observable<ResponseWrapper<List<TVShow>>> data;
        if (forceRemote) {
            data = getRecommendedFromApi();
        } else {
            data = tvShowDao.getRecommended().map(ResponseWrapper::success);
        }
        return Observable.concat(getRecommendedFromDB(), data);
    }

    private Observable<ResponseWrapper<List<TVShow>>> getRecommendedFromApi() {
        return tvShowManager.getRecommended()
                .map((Function<ResponseModel<ArrayList<TVShow>>, ResponseWrapper<List<TVShow>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<TVShow> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<TVShow> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            for (TVShow tvShow : apiResponse.data) {
                                tvShow.setRecommended(true);
                                preventFieldOverriding(tvShow);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<TVShow>>> getRecommendedFromDB() {
        return tvShowDao.getRecommended()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch recommended tv shows from server and store it to database
     */

    public Observable<ResponseWrapper<List<TVShow>>> getTrending(boolean forceRemote) {
        Observable<ResponseWrapper<List<TVShow>>> data;
        if (forceRemote) {
            data = getTrendingFromApi();
        } else {
            data = tvShowDao.getTrending().map(ResponseWrapper::success);
        }
        return Observable.concat(getTrendingFromDB(), data);
    }

    private Observable<ResponseWrapper<List<TVShow>>> getTrendingFromApi() {
        return tvShowManager.getTrending()
                .map((Function<ResponseModel<ArrayList<TVShow>>, ResponseWrapper<List<TVShow>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<TVShow> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<TVShow> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            for (TVShow tvShow : apiResponse.data) {
                                tvShow.setTrending(true);
                                preventFieldOverriding(tvShow);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<TVShow>>> getTrendingFromDB() {
        return tvShowDao.getTrending()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch recently added tv shows from server and store it to database
     */

    public Observable<ResponseWrapper<List<TVShow>>> getRecentlyAdded(boolean forceRemote, int page) {
        Observable<ResponseWrapper<List<TVShow>>> data;
        if (forceRemote) {
            data = getRecentlyAddedFromAPI(page);
        } else {
            data = tvShowDao.getAll().map(ResponseWrapper::success);
        }
        return Observable.concat(getRecentlyAddedFromDB(), data);
    }

    private Observable<ResponseWrapper<List<TVShow>>> getRecentlyAddedFromAPI(int page) {
        return tvShowManager.getRecentlyAdded(page)
                .map((Function<ResponseModel<PagedData<TVShow>>, ResponseWrapper<List<TVShow>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<TVShow> data = response.getData().getItems();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<TVShow> data = response.getData().getItems();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            for (TVShow tvShow : apiResponse.data) {
                                preventFieldOverriding(tvShow);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<TVShow>>> getRecentlyAddedFromDB() {
        return tvShowDao.getAll()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch all tv shows by genre ID from server and store it to database
     */

    public Observable<ResponseWrapper<List<TVShow>>> getByGenreID(boolean forceRemote, int page, int genreId) {
        Observable<ResponseWrapper<List<TVShow>>> data;
        if (forceRemote) {
            data = getByGenreIDFromAPI(page, genreId);
        } else {
            data = tvShowDao.getByGenreId(genreId).map(ResponseWrapper::success);
        }
        return Observable.concat(getByGenreIDFromDB(genreId), data);
    }

    private Observable<ResponseWrapper<List<TVShow>>> getByGenreIDFromAPI(int page, int genreId) {
        return tvShowManager.getByGenreID(page, genreId)
                .map((Function<ResponseModel<PagedData<TVShow>>, ResponseWrapper<List<TVShow>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<TVShow> data = response.getData().getItems();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<TVShow> data = response.getData().getItems();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        tvShowDao.insert(apiResponse.data);
                    }
                });
    }

    private Observable<ResponseWrapper<List<TVShow>>> getByGenreIDFromDB(int genreId) {
        return tvShowDao.getByGenreId(genreId)
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch season episodes
     */
    public Observable<ResponseWrapper<List<Vod>>> getEpisodes(int page, int tvShowId, int seasonId) {
        return tvShowManager.getEpisodes(page, tvShowId, seasonId)
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
                        vodDao.insert(apiResponse.data);
                    }
                });
    }

    /**
     * Use this function to prevent field overriding
     * for custom flags
     */
    private void preventFieldOverriding(TVShow tvShow) {
        TVShow saved = tvShowDao.getById(tvShow.getId());
        if (saved != null) {
            if (saved.isRecommended() || tvShow.isRecommended()) {
                tvShow.setRecommended(true);
            }
            if (saved.isTrending() || tvShow.isTrending()) {
                tvShow.setTrending(true);
            }
        }
        tvShowDao.insert(tvShow);
    }
}
