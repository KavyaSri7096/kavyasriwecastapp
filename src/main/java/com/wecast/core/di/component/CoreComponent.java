package com.wecast.core.di.component;

import com.wecast.core.analytics.SocketManager;
import com.wecast.core.data.api.ApiInterceptor;
import com.wecast.core.data.api.manager.AccountManager;
import com.wecast.core.data.api.manager.BannerManager;
import com.wecast.core.data.api.manager.ChannelManager;
import com.wecast.core.data.api.manager.ComposerManager;
import com.wecast.core.data.api.manager.HighlightedManager;
import com.wecast.core.data.api.manager.TVGuideManager;
import com.wecast.core.data.api.manager.TVShowManager;
import com.wecast.core.data.api.manager.VodManager;
import com.wecast.core.data.api.service.AccountService;
import com.wecast.core.data.api.service.BannerService;
import com.wecast.core.data.api.service.ChannelService;
import com.wecast.core.data.api.service.ComposerService;
import com.wecast.core.data.api.service.HighlightedService;
import com.wecast.core.data.api.service.TVGuideService;
import com.wecast.core.data.api.service.TVShowService;
import com.wecast.core.data.api.service.VodService;
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
import com.wecast.core.data.db.pref.PreferenceManager;
import com.wecast.core.data.repository.BannerRepository;
import com.wecast.core.data.repository.ChannelGenreRepository;
import com.wecast.core.data.repository.ChannelRepository;
import com.wecast.core.data.repository.ComposerRepository;
import com.wecast.core.data.repository.HighlightedRepository;
import com.wecast.core.data.repository.ReminderRepository;
import com.wecast.core.data.repository.TVGuideRepository;
import com.wecast.core.data.repository.TVShowGenreRepository;
import com.wecast.core.data.repository.TVShowRepository;
import com.wecast.core.data.repository.VodGenreRepository;
import com.wecast.core.data.repository.VodRepository;
import com.wecast.core.di.module.AnalyticsModule;
import com.wecast.core.di.module.ApiModule;
import com.wecast.core.di.module.CoreModule;
import com.wecast.core.di.module.DatabaseModule;
import com.wecast.core.di.module.HttpModule;
import com.wecast.core.utils.ReminderUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ageech@live.com
 */

@Singleton
@Component(
        modules = {
                CoreModule.class,
                HttpModule.class,
                ApiModule.class,
                DatabaseModule.class,
                AnalyticsModule.class
        }
)
public interface CoreComponent {

    ApiInterceptor apiInterceptor();

    PreferenceManager preferenceManager();

    ComposerService ComposerService();

    ComposerManager composerManager();

    ComposerDao composeDao();

    ComposerRepository composerRepository();

    AccountService accountService();

    AccountManager accountManager();

    BannerService bannerService();

    BannerManager bannerManager();

    BannerRepository bannerRepository();

    ChannelService channelService();

    ChannelManager channelManager();

    ChannelDao channelDao();

    ChannelRepository channelRepository();

    ChannelGenreDao channelGenreDao();

    ChannelGenreRepository channelGenreRepository();

    HighlightedService highlightedService();

    HighlightedManager highlightedManager();

    HighlightedDao highlightedDao();

    HighlightedRepository highlightedRepository();

    VodService vodService();

    VodManager vodManager();

    VodDao vodDao();

    VodGenreDao vodGenreDao();

    VodRepository vodRepository();

    VodGenreRepository vodGenreReository();

    TVShowService tvShowService();

    TVShowManager tvShowManager();

    TVShowDao tvShowDao();

    TVShowGenreDao tvShowGenreDao();

    TVShowRepository tvShowRepository();

    TVShowGenreRepository tvShowGenreRepository();

    TVGuideService tvGuideService();

    TVGuideManager tvGuideManager();

    TVGuideDao tvGuideDao();

    TVGuideRepository tvGuideRepository();

    ReminderDao reminderDao();

    ReminderRepository reminderRepository();

    ReminderUtils reminderUtils();

    SocketManager socketManager();

    DatabaseManager databaseManager();
}