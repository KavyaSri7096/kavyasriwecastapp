package com.wecast.core.data.repository;

import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.manager.TVGuideManager;
import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.TVGuideDao;
import com.wecast.core.data.db.entities.TVGuide;
import com.wecast.core.data.db.entities.TVGuideProgramme;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by ageech@live.com
 */

@Singleton
public class TVGuideRepository {

    private final TVGuideDao tvGuideDao;
    private final TVGuideManager tvGuideManager;

    @Inject
    public TVGuideRepository(TVGuideDao tvGuideDao, TVGuideManager tvGuideManager) {
        this.tvGuideDao = tvGuideDao;
        this.tvGuideManager = tvGuideManager;
    }

    public TVGuide getById(int id) {
        return tvGuideDao.getById(id);
    }

    public TVGuideProgramme getProgrammeById(String id) {
        return tvGuideDao.getProgrammeById(id);
    }

    /**
     * Fetch all tv guide content from server and store it to database
     */

    public Observable<ResponseWrapper<List<TVGuide>>> getAll(boolean forceRemote, int page, String start, String end) {
        Observable<ResponseWrapper<List<TVGuide>>> data;
        if (forceRemote) {
            data = getAllFromAPI(page, start, end);
        } else {
            data = tvGuideDao.getAll().map(ResponseWrapper::success);
        }
        return Observable.concat(getAllFromDB(), data);
    }

    private Observable<ResponseWrapper<List<TVGuide>>> getAllFromAPI(int page, String start, String end) {
        return tvGuideManager.getPage(page, start, end)
                .map((Function<ResponseModel<PagedData<TVGuide>>, ResponseWrapper<List<TVGuide>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<TVGuide> data = response.getData().getItems();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<TVGuide> data = response.getData().getItems();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            tvGuideDao.insert(apiResponse.data);
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<TVGuide>>> getAllFromDB() {
        return tvGuideDao.getAll()
                .map(ResponseWrapper::loading)
                .take(1);
    }

    /**
     * Fetch current programmes from server and store it to database
     */

    public Observable<ResponseWrapper<TVGuide>> getProgrammes(boolean forceRemote, int page, int channelId, String start, String end) {
        Observable<ResponseWrapper<TVGuide>> data;
        if (forceRemote) {
            data = getProgrammesFromAPI(page, channelId, start, end);
        } else {
            data = tvGuideDao.getProgrammes(channelId).map(ResponseWrapper::success);
        }
        return Observable.concat(getProgrammesFromDB(channelId), data);
    }

    private Observable<ResponseWrapper<TVGuide>> getProgrammesFromAPI(int page, int channelId, String start, String end) {
        return tvGuideManager.getProgrammesById(page, channelId, start, end)
                .map((Function<ResponseModel<PagedData<TVGuide>>, ResponseWrapper<TVGuide>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        TVGuide data = response.getData().getItems().get(0);
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        TVGuide data = response.getData().getItems().get(0);
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            tvGuideDao.insert(apiResponse.data);
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<TVGuide>> getProgrammesFromDB(int channelId) {
        return tvGuideDao.getProgrammes(channelId)
                .map(ResponseWrapper::loading)
                .take(1);
    }
}
