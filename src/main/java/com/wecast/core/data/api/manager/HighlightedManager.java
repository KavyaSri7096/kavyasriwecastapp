package com.wecast.core.data.api.manager;

import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.api.service.HighlightedService;
import com.wecast.core.data.db.entities.Highlighted;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */

public class HighlightedManager {

    private HighlightedService highlightedService;

    public HighlightedManager(HighlightedService highlightedService) {
        this.highlightedService = highlightedService;
    }

    public Observable<ResponseModel<ArrayList<Highlighted>>> getAll() {
        return highlightedService.getAll();
    }
}
