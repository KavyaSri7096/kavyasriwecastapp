package com.wecast.core.data.api.manager;

import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.api.service.BannerService;
import com.wecast.core.data.db.entities.Banner;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */

public class BannerManager {

    private BannerService bannerService;

    public BannerManager(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    public Observable<ResponseModel<Banner>> getBanner(String position) {
        return bannerService.getBanner(position);
    }
}
