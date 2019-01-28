package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.Channel;
import com.wecast.core.data.db.entities.ChannelGenre;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

public class ChannelGenreDao extends BaseDao<ChannelGenre> {

    private final Realm realm;

    public ChannelGenreDao(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insert(final ChannelGenre data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public void insert(final List<ChannelGenre> data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public Observable<List<ChannelGenre>> getAll() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(ChannelGenre.class).findAll());
            }
        });
    }

    @Override
    public ChannelGenre getById(final int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            ChannelGenre item = realm.where(ChannelGenre.class).equalTo("id", id).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return (int) realm.where(ChannelGenre.class).count();
    }

    @Override
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(Channel.class));
        realm.close();
    }
}
