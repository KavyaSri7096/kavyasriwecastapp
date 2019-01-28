package com.wecast.core.di.module;

import com.wecast.core.Constants;
import com.wecast.core.data.api.manager.ComposerManager;
import com.wecast.core.data.db.DatabaseManager;
import com.wecast.core.data.db.dao.ChannelDao;
import com.wecast.core.data.db.dao.ChannelGenreDao;
import com.wecast.core.data.db.dao.ComposerDao;
import com.wecast.core.data.db.dao.HighlightedDao;
import com.wecast.core.data.db.dao.ReminderDao;
import com.wecast.core.data.db.dao.TVGuideDao;
import com.wecast.core.data.db.dao.TVShowDao;
import com.wecast.core.data.db.dao.TVShowGenreDao;
import com.wecast.core.data.db.dao.VodDao;
import com.wecast.core.data.db.dao.VodGenreDao;
import com.wecast.core.data.db.pref.AppPreferenceManager;
import com.wecast.core.data.db.pref.PreferenceManager;
import com.wecast.core.data.repository.ComposerRepository;
import com.wecast.core.di.PreferenceInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

@Module
public class DatabaseModule {

    /**
     * SHARED PREFERENCES
     */

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return Constants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferenceManager providePreferencesManager(AppPreferenceManager appPreferencesManager) {
        return appPreferencesManager;
    }

    /**
     * REALM DATABASE
     */

    @Provides
    Realm providesRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    ComposerDao provideComposerDao(Realm realm) {
        return new ComposerDao(realm);
    }

    @Provides
    @Singleton
    HighlightedDao provideHighlightedDao(Realm realm) {
        return new HighlightedDao(realm);
    }

    @Provides
    @Singleton
    ChannelDao provideChannelDao(Realm realm) {
        return new ChannelDao(realm);
    }

    @Provides
    @Singleton
    ChannelGenreDao provideChannelGenreDao(Realm realm) {
        return new ChannelGenreDao(realm);
    }

    @Provides
    VodDao provideVodDao(Realm realm) {
        return new VodDao(realm);
    }

    @Provides
    VodGenreDao provideVodGenreDao(Realm realm) {
        return new VodGenreDao(realm);
    }

    @Provides
    @Singleton
    TVShowDao provideTVShowDao(Realm realm) {
        return new TVShowDao(realm);
    }

    @Provides
    @Singleton
    TVShowGenreDao provideTVShowGenreDao(Realm realm) {
        return new TVShowGenreDao(realm);
    }

    @Provides
    @Singleton
    TVGuideDao provideTVGuideDao(Realm realm) {
        return new TVGuideDao(realm);
    }

    @Provides
    @Singleton
    ReminderDao provideReminderDao(Realm realm) {
        return new ReminderDao(realm);
    }

    @Provides
    @Singleton
    DatabaseManager provideDatabaseManager(ComposerDao composerDao, HighlightedDao highlightedDao, ChannelDao channelDao, ChannelGenreDao channelGenreDao, TVGuideDao tvGuideDao,
                                           VodDao vodDao, VodGenreDao vodGenreDao, TVShowDao tvShowDao, TVShowGenreDao tvShowGenreDao, ReminderDao reminderDao) {
        return new DatabaseManager(composerDao, highlightedDao, channelDao, channelGenreDao, tvGuideDao, vodDao, vodGenreDao, tvShowDao, tvShowGenreDao, reminderDao);
    }
}
