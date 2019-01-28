package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.Channel;
import com.wecast.core.data.db.entities.ChannelGenre;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

public class ChannelDao extends BaseDao<Channel> {

    private final Realm realm;

    public ChannelDao(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insert(final Channel data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public void insert(final List<Channel> data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public Observable<List<Channel>> getAll() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(Channel.class).sort("channelNumber").findAll());
            }
        });
    }

    @Override
    public Channel getById(final int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            Channel item = realm.where(Channel.class).equalTo("id", id).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return (int) realm.where(Channel.class).count();
    }

    @Override
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(Channel.class));
        realm.close();
    }

    public List<Channel> getAllAsList() {
        try (Realm realm = Realm.getDefaultInstance()) {
            return realm.copyFromRealm(realm.where(Channel.class).sort("channelNumber").findAll());
        }
    }

    public List<Channel> getFavoritesAsList() {
        try (Realm realm = Realm.getDefaultInstance()) {
            String filed = "favorite";
            return realm.copyFromRealm(realm.where(Channel.class).equalTo(filed, true).findAll());
        }
    }

    public Observable<List<Channel>> getFavorites() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                String filed = "favorite";
                return realm.copyFromRealm(realm.where(Channel.class).equalTo(filed, true).findAll());
            }
        });
    }

    public Observable<List<Channel>> getTrending() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                String filed = "isTrending";
                return realm.copyFromRealm(realm.where(Channel.class).equalTo(filed, true).findAll());
            }
        });
    }

    public Observable<List<Channel>> getByGenreId(int genreId) {
        List<Channel> results = getAllAsList();
        List<Channel> data = new ArrayList<>();
        if (results != null && results.size() > 0) {
            for (Channel channel : results) {
                for (ChannelGenre channelGenre : channel.getCategories()) {
                    if (channelGenre.getId() == genreId) {
                        data.add(channel);
                    }
                }
            }
        }
        return Observable.fromCallable(() -> data);
    }
}
