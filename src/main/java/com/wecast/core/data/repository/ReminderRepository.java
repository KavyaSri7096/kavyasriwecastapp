package com.wecast.core.data.repository;

import com.wecast.core.data.api.ApiStatus;
import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.manager.TVGuideManager;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.ReminderDao;
import com.wecast.core.data.db.entities.TVGuideReminder;

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
public class ReminderRepository {

    private final ReminderDao reminderDao;
    private final TVGuideManager tvGuideManager;

    @Inject
    public ReminderRepository(ReminderDao reminderDao, TVGuideManager tvGuideManager) {
        this.reminderDao = reminderDao;
        this.tvGuideManager = tvGuideManager;
    }

    public TVGuideReminder getById(int id) {
        return reminderDao.getById(id);
    }

    /**
     * Fetch all reminders from server and store it to database
     */

    public Observable<ResponseWrapper<List<TVGuideReminder>>> getAll(boolean forceRemote) {
        Observable<ResponseWrapper<List<TVGuideReminder>>> data;
        if (forceRemote) {
            data = getAllFromAPI();
        } else {
            data = reminderDao.getAll().map(ResponseWrapper::success);
        }
        return Observable.concat(getAllFromDB(), data);
    }

    private Observable<ResponseWrapper<List<TVGuideReminder>>> getAllFromAPI() {
        return tvGuideManager.getReminders()
                .map((Function<ResponseModel<ArrayList<TVGuideReminder>>, ResponseWrapper<List<TVGuideReminder>>>) response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        List<TVGuideReminder> data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        List<TVGuideReminder> data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                })
                .doOnNext(apiResponse -> {
                    if (apiResponse.status == ApiStatus.SUCCESS) {
                        if (apiResponse.data != null) {
                            reminderDao.insert(apiResponse.data);
                        }
                    }
                });
    }

    private Observable<ResponseWrapper<List<TVGuideReminder>>> getAllFromDB() {
        return reminderDao.getAll()
                .map(ResponseWrapper::loading)
                .take(1);
    }
}
