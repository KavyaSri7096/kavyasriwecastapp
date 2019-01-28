package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.Highlighted;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

public class HighlightedDao extends BaseDao<Highlighted> {

    private final Realm realm;

    public HighlightedDao(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insert(final Highlighted data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public void insert(final List<Highlighted> data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public Observable<List<Highlighted>> getAll() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(Highlighted.class).findAll());
            }
        });
    }

    @Override
    public Highlighted getById(final int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            Highlighted item = realm.where(Highlighted.class).equalTo("id", id).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return (int) realm.where(Highlighted.class).count();
    }

    @Override
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(Highlighted.class));
        realm.close();
    }
}
