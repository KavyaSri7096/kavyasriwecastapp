package com.wecast.core.data.api.manager;

import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.api.service.TVGuideService;
import com.wecast.core.data.db.entities.TVGuide;
import com.wecast.core.data.db.entities.TVGuideReminder;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */

public class TVGuideManager {

    private final TVGuideService tvGuideService;

    public TVGuideManager(TVGuideService tvGuideService) {
        this.tvGuideService = tvGuideService;
    }

    public Observable<ResponseModel<ArrayList<TVGuide>>> get(String start, String end) {
        return tvGuideService.get(start, end, 1);
    }

    public Observable<ResponseModel<PagedData<TVGuide>>> getPage(int page, String start, String end) {
        return tvGuideService.getPage(page, start, end, 1);
    }

    public Observable<ResponseModel<PagedData<TVGuide>>> getCurrentProgrammes(int page) {
        return tvGuideService.getCurrentProgrammes(page, 1, 1);
    }

    public Observable<ResponseModel<PagedData<TVGuide>>> getCurrentProgrammeById(int page, int id) {
        return tvGuideService.getCurrentProgrammeByID(page, 1, 1, id);
    }

    public Observable<ResponseModel<PagedData<TVGuide>>> getProgrammesById(int page, int id, String start, String end) {
        return tvGuideService.getPage(page, id, start, end, 1);
    }

    public Observable<ResponseModel<ArrayList<TVGuide>>> getEpgByID(Integer id) {
        return tvGuideService.getEpgByID(id);
    }

    public Observable<ResponseModel<ArrayList<TVGuideReminder>>> getReminders() {
        return tvGuideService.getReminders();
    }

    public Observable<ResponseModel<TVGuideReminder>> addReminder(int channelId, int epgChannelId, String epgProgrammeStringId) {
        return tvGuideService.addReminder(channelId, epgChannelId, epgProgrammeStringId, "10");
    }

    public Observable<ResponseModel<TVGuideReminder>> editReminder(int id, String remindMe) {
        return tvGuideService.editReminder(id, remindMe);
    }

    public Observable<ResponseModel> removeReminder(int id) {
        return tvGuideService.removeReminder(id);
    }
}
