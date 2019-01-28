package com.wecast.core.di.wrapper;

import android.app.Application;

import com.wecast.core.di.component.CoreComponent;
import com.wecast.core.di.component.DaggerCoreComponent;
import com.wecast.core.di.module.AnalyticsModule;
import com.wecast.core.di.module.ApiModule;
import com.wecast.core.di.module.CoreModule;
import com.wecast.core.di.module.DatabaseModule;
import com.wecast.core.di.module.HttpModule;

/**
 * Created by ageech@live.com
 */

public class CoreComponentWrapper {

    private static CoreComponentWrapper componentWrapper;

    private static CoreComponentWrapper getInstance(Application application) {
        if (componentWrapper == null) {
            synchronized (CoreComponentWrapper.class) {
                if (componentWrapper == null) {
                    componentWrapper = new CoreComponentWrapper();
                    componentWrapper.initializeComponent(application);
                }
            }
        }
        return componentWrapper;
    }

    private CoreComponent component;

    public static CoreComponent getBaseComponent(Application application) {
        CoreComponentWrapper appComponentWrapper = getInstance(application);
        return appComponentWrapper.component;
    }

    private void initializeComponent(Application application) {
        component = DaggerCoreComponent.builder()
                .coreModule(new CoreModule(application))
                .httpModule(new HttpModule())
                .apiModule(new ApiModule())
                .databaseModule(new DatabaseModule())
                .analyticsModule(new AnalyticsModule())
                .build();
    }
}
