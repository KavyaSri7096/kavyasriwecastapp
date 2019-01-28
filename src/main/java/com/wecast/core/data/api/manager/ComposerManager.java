package com.wecast.core.data.api.manager;

import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.api.service.ComposerService;
import com.wecast.core.data.db.entities.composer.Composer;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */

public class ComposerManager {

    private ComposerService composerService;

    public ComposerManager( ComposerService composerService) {
        this.composerService = composerService;
    }

    public Observable<ResponseModel<Composer>> getComposer() {
        return composerService.getComposer();
    }
}
