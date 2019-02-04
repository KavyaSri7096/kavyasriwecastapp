package com.wecast.core.data.repository;

import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.manager.ChannelManager;
import com.wecast.core.data.api.manager.HighlightedManager;
import com.wecast.core.data.api.manager.VodManager;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.ChannelDao;
import com.wecast.core.data.db.dao.HighlightedDao;
import com.wecast.core.data.db.dao.TVShowDao;
import com.wecast.core.data.db.dao.VodDao;
import com.wecast.core.data.db.entities.Channel;
import com.wecast.core.data.db.entities.Highlighted;
import com.wecast.core.data.db.entities.HighlightedType;

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
    private final ChannelDao channelDao;
    private final VodDao vodDao;
    private final TVShowDao tvShowDao;

    @Inject
    public HighlightedRepository(HighlightedDao highlightedDao, HighlightedManager highlightedManager,
                                 ChannelDao channelDao, VodDao vodDao, TVShowDao tvShowDao) {
        this.highlightedDao = highlightedDao;
        this.highlightedManager = highlightedManager;
        this.channelDao = channelDao;
        this.vodDao = vodDao;
        this.tvShowDao = tvShowDao;
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
                            // Clear data from database
                            highlightedDao.clear();
                            // Insert new data from server
                            for (Highlighted highlighted : apiResponse.data) {
                                String model = highlighted.getModel().toString();
                                highlighted.setModelString(model);
                                highlightedDao.insert(highlighted);
                                if (highlighted.getType() == HighlightedType.CHANNEL) {
                                    channelDao.insert(highlighted.getChannelModel());
                                } else if (highlighted.getType() == HighlightedType.MOVIE
                                        || highlighted.getType() == HighlightedType.EPISODE) {
                                    vodDao.insert(highlighted.getMovieModel());
                                } else if (highlighted.getType() == HighlightedType.TV_SHOW) {
                                    tvShowDao.insert(highlighted.getTVShowModel());
                                }
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
