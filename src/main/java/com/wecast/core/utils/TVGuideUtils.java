package com.wecast.core.utils;

import androidx.core.util.Pair;

import com.wecast.core.Constants;
import com.wecast.core.Logger;
import com.wecast.core.data.db.entities.TVGuide;
import com.wecast.core.data.db.entities.TVGuideProgramme;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ageech@live.com
 */

public final class TVGuideUtils {

    public static Pair<String, String> getStartAndEnd() {
        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
        String start = String.valueOf(currentTimeInSeconds);
        String stop = String.valueOf(currentTimeInSeconds + 24 * 60 * 60);
        return new Pair<>(start, stop);
    }

    public static boolean checkDoWeNeedToUpdateEpg(List<TVGuide> data) {
        if (data != null && !data.isEmpty()) {
            long currentTimestamp = new Date().getTime();
            long savedTimestamp = data.get(0).getTimestamp();
            long timeDiff = currentTimestamp - savedTimestamp;

            int seconds = (int) timeDiff / 1000;
            int hours = seconds / 3600;

            Logger.d("HOURS: " + hours + " (" + timeDiff + ", " + seconds + ")");
            return (timeDiff > Constants.MIN_EPG_UPDATE_INTERVAL);
        }
        return true;
    }

    public static String getStartEnd(TVGuideProgramme programme) {
        if (programme != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date start = programme.getStartDate();
            Date stop = programme.getStopDate();
            return sdf.format(start) + " - " + sdf.format(stop);
        }
        return "";
    }

    public static String getStart(TVGuideProgramme programme) {
        if (programme != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date start = programme.getStartDate();
            return sdf.format(start);
        }
        return "";
    }

    public static int getMax(TVGuideProgramme programme) {
        if (programme != null) {
            Date start = programme.getStartDate();
            Date stop = programme.getStopDate();
            return (int) (stop.getTime() - start.getTime()) / 1000 / 60;
        }
        return 0;
    }

    public static int getProgress(TVGuideProgramme programme) {
        if (programme != null) {
            Date stop = programme.getStopDate();
            int progress = (int) (stop.getTime() - Calendar.getInstance().getTimeInMillis()) / 1000 / 60;
            return getMax(programme) - progress;
        }
        return 0;
    }
}
