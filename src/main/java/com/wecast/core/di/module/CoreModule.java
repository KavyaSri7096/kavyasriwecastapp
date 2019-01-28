package com.wecast.core.di.module;

import android.app.Application;
import android.content.Context;

import com.wecast.core.utils.ReminderUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ageech@live.com
 */

@Module
public class CoreModule {

    private Application application;

    public CoreModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    ReminderUtils provideReminderUtils(Context context) {
        return new ReminderUtils(context);
    }
}