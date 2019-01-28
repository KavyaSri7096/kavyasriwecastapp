package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.TVShowGenre;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

public class TVShowGenreDao extends BaseDao<TVShowGenre> {

    private final Realm realm;

    public TVShowGenreDao(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insert(final TVShowGenre data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public void insert(final List<TVShowGenre> data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public Observable<List<TVShowGenre>> getAll() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(TVShowGenre.class).findAll());
            }
        });
    }

    @Override
    public TVShowGenre getById(final int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            TVShowGenre item = realm.where(TVShowGenre.class).equalTo("id", id).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return (int) realm.where(TVShowGenre.class).count();
    }

    @Override
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(TVShowGenre.class));
        realm.close();
    }
}
