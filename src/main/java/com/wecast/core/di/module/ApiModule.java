package com.wecast.core.di.module;

import com.wecast.core.data.api.manager.AccountManager;
import com.wecast.core.data.api.manager.BannerManager;
import com.wecast.core.data.api.manager.ComposerManager;
import com.wecast.core.data.api.manager.HighlightedManager;
import com.wecast.core.data.api.manager.TVGuideManager;
import com.wecast.core.data.api.manager.VodManager;
import com.wecast.core.data.api.service.AccountService;
import com.wecast.core.data.api.service.BannerService;
import com.wecast.core.data.api.service.ChannelService;
import com.wecast.core.data.api.service.ComposerService;
import com.wecast.core.data.api.service.HighlightedService;
import com.wecast.core.data.api.service.TVGuideService;
import com.wecast.core.data.api.service.TVShowService;
import com.wecast.core.data.api.service.VodService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ageech@live.com
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    ComposerService provideComposerService(Retrofit retrofit) {
        return retrofit.create(ComposerService.class);
    }

    @Provides
    @Singleton
    ComposerManager provideComposerManager(ComposerService composerService) {
        return new ComposerManager(composerService);
    }

    @Provides
    @Singleton
    AccountService provideAccountService(Retrofit retrofit) {
        return retrofit.create(AccountService.class);
    }

    @Provides
    @Singleton
    AccountManager provideAccountManager(AccountService accountService) {
        return new AccountManager(accountService);
    }

    @Provides
    @Singleton
    BannerService provideBannerService(Retrofit retrofit) {
        return retrofit.create(BannerService.class);
    }

    @Provides
    @Singleton
    BannerManager provideBannerManager(BannerService bannerService) {
        return new BannerManager(bannerService);
    }

    @Provides
    @Singleton
    HighlightedService provideHighlightedService(Retrofit retrofit) {
        return retrofit.create(HighlightedService.class);
    }

    @Provides
    @Singleton
    VodService provideVodService(Retrofit retrofit) {
        return retrofit.create(VodService.class);
    }

    @Provides
    @Singleton
    ChannelService provideChannelService(Retrofit retrofit) {
        return retrofit.create(ChannelService.class);
    }

    @Provides
    @Singleton
    HighlightedManager provideHighlightedManager(HighlightedService highlightedService) {
        return new HighlightedManager(highlightedService);
    }

    @Provides
    @Singleton
    VodManager provideVodManager(VodService vodService) {
        return new VodManager(vodService);
    }

    @Provides
    @Singleton
    TVShowService provideTVShowService(Retrofit retrofit) {
        return retrofit.create(TVShowService.class);
    }

    @Provides
    @Singleton
    TVGuideManager provideTVGuideManager(TVGuideService tvGuideService) {
        return new TVGuideManager(tvGuideService);
    }

    @Provides
    @Singleton
    TVGuideService provideTVGuideService(Retrofit retrofit) {
        return retrofit.create(TVGuideService.class);
    }
}
