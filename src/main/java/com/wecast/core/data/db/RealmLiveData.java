package com.wecast.core.data.db;

import androidx.lifecycle.LiveData;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by ageech@live.com
 */

public class RealmLiveData<T extends RealmModel> extends LiveData<RealmResults<T>> {

    private RealmResults<T> realmResults;
    private final RealmChangeListener<RealmResults<T>> realmChangeListener = this::setValue;

    public RealmLiveData(RealmResults<T> realmResults) {
        this.realmResults = realmResults;
    }

    @Override
    protected void onActive() {
        realmResults.addChangeListener(realmChangeListener);
    }

    @Override
    protected void onInactive() {
        realmResults.removeChangeListener(realmChangeListener);
    }
}