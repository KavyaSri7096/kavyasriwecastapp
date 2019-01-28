package com.wecast.core.data.repository;

import com.wecast.core.data.api.ResponseWrapper;
import com.wecast.core.data.api.manager.BannerManager;
import com.wecast.core.data.db.entities.Banner;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */

@Singleton
public class BannerRepository {

    private final BannerManager bannerManager;

    @Inject
    public BannerRepository(BannerManager bannerManager) {
        this.bannerManager = bannerManager;
    }

    public Observable<ResponseWrapper<Banner>> getBanner(String boxPosition) {
        return bannerManager.getBanner(boxPosition)
                .map(response -> {
                    if (response.isTokenExpired()) {
                        return ResponseWrapper.tokenExpired();
                    } else if (response.isSubscriptionExpired()) {
                        Banner data = response.getData();
                        return ResponseWrapper.subscriptionExpired(data);
                    } else if (response.isSuccessful()) {
                        Banner data = response.getData();
                        return ResponseWrapper.success(data);
                    } else {
                        return ResponseWrapper.error(response.getMessage());
                    }
                });
    }
}
