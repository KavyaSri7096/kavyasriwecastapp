package com.wecast.core.service;

import android.app.job.JobService;

import dagger.android.AndroidInjection;

/**
 * Created by ageech@live.com
 */

public abstract class BaseService extends JobService {

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }
}
