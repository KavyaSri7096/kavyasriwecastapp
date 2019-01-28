package com.wecast.core.di.module;

import com.wecast.core.analytics.SocketManager;
import com.wecast.core.data.db.pref.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ageech@live.com
 */

@Module
public class AnalyticsModule {


    @Provides
    @Singleton
    SocketManager provideSocketManager(PreferenceManager preferenceManager) {
        return new SocketManager(preferenceManager);
    }
}
