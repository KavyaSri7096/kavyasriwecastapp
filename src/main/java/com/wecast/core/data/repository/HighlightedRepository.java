package com.wecast.core.data.repository;

import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.manager.HighlightedManager;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.HighlightedDao;
import com.wecast.core.data.db.entities.Highlighted;

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
public class HighlightedRepository {

    private final HighlightedDao highlightedDao;
    private final HighlightedManager highlightedManager;

    @Inject
    public HighlightedRepository(HighlightedDao highlightedDao, HighlightedManager highlightedManager) {
        this.highlightedDao = highlightedDao;
        this.highlightedManager = highlightedManager;
    }

    public Highlighted getByID(int id) {
        return highlightedDao.getById(id);
    }

    /**
     * Fetch all highlighted content from server and store it to database
     */

    public Observable<ResponseWrapper<List<Highlighted>>> getAll(boolean forceRemote) {
        Observable<ResponseWrapper<List<Highlighted>>> data;
        if (forceRemote) {
            data = getAllFromAPI();
        } else {
            data = highlightedDao.getAll().map(ResponseWrapper::success);
        }
        return Observable.concat(getAllFromDB(), data);
    }

    private Observable<ResponseWrapper<List<Highlighted>>> getAllFromAPI() {
        return highlightedManager.getAll()
                .map((Function<ResponseModel<ArrayList<Highlighted>>, ResponseWrapper<List<Highlighted>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<Highlighted> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<Highlighted> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            for (Highlighted highlighted : apiResponse.data) {
                                String model = highlighted.getModel().toString();
                                highlighted.setModelString(model);
                                highlightedDao.insert(highlighted);
                            }
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<Highlighted>>> getAllFromDB() {
        return highlightedDao.getAll()
                .map(ResponseWrapper::loading)
                .take(1);
    }
}
