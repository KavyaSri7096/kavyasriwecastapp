package com.wecast.core.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import com.wecast.core.data.db.entities.TVGuideProgramme;
import com.wecast.core.data.db.entities.TVGuideReminder;

import java.util.TimeZone;

/**
 * Created by ageech@live.com
 */

public final class ReminderUtils {

    private static final String ACCOUNT_NAME = "com.wecast.app";
    private static final String ACCOUNT_OWNER = "clendar@wetek.com";
    private static final String CALENDAR_DISPLAY_NAME = "WeCast Calendar";

    private ContentResolver contentResolver;

    public ReminderUtils(Context context) {
        this.contentResolver = context.getContentResolver();
    }

    @SuppressLint("MissingPermission")
    private long getCalendarId() {
        String[] projection = new String[]{
                CalendarContract.Calendars._ID
        };
        String selection = CalendarContract.Calendars.ACCOUNT_NAME + " = ? AND " + CalendarContract.Calendars.ACCOUNT_TYPE + " = ? ";
        // Use the same values as above:
        String[] selArgs = new String[]{
                ACCOUNT_NAME,
                CalendarContract.ACCOUNT_TYPE_LOCAL
        };
        Cursor cursor = contentResolver.query(CalendarContract.Calendars.CONTENT_URI, projection, selection, selArgs, null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                long id = cursor.getLong(0);
                cursor.close();
                return id;
            }
        }
        return -1;
    }

    public void createCalendar() {
        removeCalendar();
        // Create new calendar
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Calendars.ACCOUNT_NAME, ACCOUNT_NAME);
        values.put(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL);
        values.put(CalendarContract.Calendars.NAME, CALENDAR_DISPLAY_NAME);
        values.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDAR_DISPLAY_NAME);
        values.put(CalendarContract.Calendars.CALENDAR_COLOR, 0xFF0FA0DB);
        values.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        values.put(CalendarContract.Calendars.OWNER_ACCOUNT, ACCOUNT_OWNER);
        values.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, TimeZone.getDefault().getID());
        values.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        Uri.Builder builder = CalendarContract.Calendars.CONTENT_URI.buildUpon();
        builder.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, ACCOUNT_NAME);
        builder.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL);
        builder.appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true");
        contentResolver.insert(builder.build(), values);
    }

    public void removeCalendar() {
        long calendarId = getCalendarId();
        if (calendarId != -1) {
            Uri deleteUri = ContentUris.withAppendedId(CalendarContract.Calendars.CONTENT_URI, calendarId);
            contentResolver.delete(deleteUri, null, null);
        }
    }

    public long getEventId(TVGuideProgramme programme) {
        long begin = programme.getStartDate().getTime();
        long end = programme.getStopDate().getTime();
        String[] projection = new String[]{
                CalendarContract.Instances._ID,
                CalendarContract.Instances.BEGIN,
                CalendarContract.Instances.END,
                CalendarContract.Instances.EVENT_ID
        };
        Cursor cursor = CalendarContract.Instances.query(contentResolver, projection, begin, end, programme.getTitle());
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                long id = Long.parseLong(cursor.getString(0));
                cursor.close();
                return id;
            }
        }
        return -1;
    }

    @SuppressLint("MissingPermission")
    public void createEvent(TVGuideProgramme programme) {
        long calId = getCalendarId();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, programme.getStartDate().getTime());
        values.put(CalendarContract.Events.DTEND, programme.getStopDate().getTime());
        values.put(CalendarContract.Events.TITLE, programme.getTitle());
        values.put(CalendarContract.Events.DESCRIPTION, programme.getDescription());
        values.put(CalendarContract.Events.CALENDAR_ID, calId);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

        Uri uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, values);
        // get the event ID that is the last element in the Uri
        if (uri != null) {
            long eventId = Long.parseLong(uri.getLastPathSegment());
            createReminder(eventId);
        }
    }

    private long getReminderId(long eventId) {
        String[] projection = new String[]{
                CalendarContract.Reminders._ID,
                CalendarContract.Reminders.METHOD,
                CalendarContract.Reminders.MINUTES
        };
        Cursor cursor = CalendarContract.Reminders.query(contentResolver, eventId, projection);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                long id = Long.parseLong(cursor.getString(0));
                cursor.close();
                return id;
            }
        }
        return -1;
    }

    @SuppressLint("MissingPermission")
    private void createReminder(long eventId) {
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, 10);
        values.put(CalendarContract.Reminders.EVENT_ID, eventId);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, values);
    }

    @SuppressLint("MissingPermission")
    public void updateReminder(long eventId, long minutes) {
        long reminderId = getReminderId(eventId);
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.MINUTES, minutes);
        String[] selArgs = new String[]{
                Long.toString(reminderId)
        };
        contentResolver.update(CalendarContract.Reminders.CONTENT_URI, values, CalendarContract.Reminders._ID + " =? ", selArgs);
    }

    @SuppressLint("MissingPermission")
    public void removeReminder(long eventId, TVGuideReminder reminder) {
        String[] selArgs = {
                reminder.getEpgProgramme().getTitle()
        };
        contentResolver.delete(CalendarContract.Events.CONTENT_URI, CalendarContract.Events.TITLE + " = ?", selArgs);
    }
}
