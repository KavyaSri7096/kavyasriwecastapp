package com.wecast.core.service;

import android.app.job.JobParameters;

import com.wecast.core.Logger;
import com.wecast.core.data.api.manager.TVGuideManager;
import com.wecast.core.data.db.dao.ReminderDao;
import com.wecast.core.data.db.entities.TVGuideProgramme;
import com.wecast.core.data.db.entities.TVGuideReminder;
import com.wecast.core.data.db.pref.PreferenceManager;
import com.wecast.core.utils.ReminderUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ageech@live.com
 */

public class SyncRemindersService extends BaseService {

    @Inject
    PreferenceManager preferenceManager;
    @Inject
    TVGuideManager tvGuideManager;
    @Inject
    ReminderUtils reminderHelper;
    @Inject
    ReminderDao reminderDao;

    private Disposable disposable;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Logger.d("SyncRemindersService", "Service started!");
        getReminders();
        return true;
    }

    private void getReminders() {
        disposable = tvGuideManager.getReminders()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.isSuccessful()) {
                        updateCalendarEvents(response.getData());
                    }
                }, Throwable::printStackTrace);
    }

    public void updateCalendarEvents(ArrayList<TVGuideReminder> data) {
        // Store reminders to database
        reminderDao.insert(data);

        // Update calendar events
        reminderHelper.createCalendar();
        for (TVGuideReminder reminder : data) {
            TVGuideProgramme programme = new TVGuideProgramme();
            programme.setId(reminder.getEpgProgramme().getId());
            programme.setStringId(reminder.getEpgProgramme().getStringId());
            programme.setTitle(reminder.getEpgProgramme().getTitle());
            programme.setDesc(reminder.getEpgProgramme().getDescription());
            programme.setStart(reminder.getEpgProgramme().getStartTimestamp());
            programme.setStop(reminder.getEpgProgramme().getStopTimestamp());
            // Add event to calendar
            long eventId = reminderHelper.getEventId(programme);
            if (eventId == -1) {
                reminderHelper.createEvent(programme);
            }
        }

        Logger.d("SyncRemindersService", "Up to date (Count = " + data.size() + ")");
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        disposable.dispose();
        Logger.d("SyncRemindersService ", "Service stopped!");
        return true;
    }
}
