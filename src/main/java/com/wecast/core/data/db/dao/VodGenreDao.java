package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.VodGenre;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

public class VodGenreDao extends BaseDao<VodGenre> {

    private final Realm realm;

    public VodGenreDao(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insert(final VodGenre data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public void insert(final List<VodGenre> data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public Observable<List<VodGenre>> getAll() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(VodGenre.class).findAll());
            }
        });
    }

    @Override
    public VodGenre getById(final int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            VodGenre item = realm.where(VodGenre.class).equalTo("id", id).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return (int) realm.where(VodGenre.class).count();
    }

    @Override
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(VodGenre.class));
        realm.close();
    }
}
