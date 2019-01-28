package com.wecast.core.data.repository;

import com.wecast.core.data.api.manager.ComposerManager;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.db.dao.ComposerDao;
import com.wecast.core.data.db.entities.composer.Composer;
import com.wecast.core.data.db.entities.composer.Modules;
import com.wecast.core.data.db.pref.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ageech@live.com
 */

@Singleton
public class ComposerRepository {

    private final PreferenceManager preferenceManager;
    private final ComposerDao composerDao;
    private final ComposerManager composerManager;

    @Inject
    public ComposerRepository(PreferenceManager preferenceManager, ComposerDao composerDao, ComposerManager composerManager) {
        this.preferenceManager = preferenceManager;
        this.composerDao = composerDao;
        this.composerManager = composerManager;
    }

    public String getAppLogo() {
        if (composerDao.getComposer() != null) {
            return composerDao.getComposer().getAndroid().getMobile().getLogos().getHdpi();
        } else {
            return null;
        }
    }

    public Modules getAppModules() {
        if (composerDao.getComposer() != null) {
            return composerDao.getComposer().getAndroid().getMobile().getModules();
        } else {
            return null;
        }
    }

    public Observable<Composer> getComposer() {
        return composerManager.getComposer()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ResponseModel::getData)
                .doOnNext(response -> {
                    preferenceManager.setSocketUrl(response.getSocketUrl());
                    composerDao.insert(response);
                });

    }
}
