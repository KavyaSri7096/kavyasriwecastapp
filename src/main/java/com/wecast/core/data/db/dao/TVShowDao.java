package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.TVShow;
import com.wecast.core.data.db.entities.TVShowGenre;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

public class TVShowDao extends BaseDao<TVShow> {

    private final Realm realm;

    public TVShowDao(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insert(final TVShow data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public void insert(final List<TVShow> data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public Observable<List<TVShow>> getAll() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(TVShow.class).findAll());
            }
        });
    }

    @Override
    public TVShow getById(final int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            TVShow item = realm.where(TVShow.class).equalTo("id", id).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return (int) realm.where(TVShow.class).count();
    }

    @Override
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(TVShow.class));
        realm.close();
    }

    public Observable<List<TVShow>> getRecommended() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                String filed = "isRecommended";
                return realm.copyFromRealm(realm.where(TVShow.class).equalTo(filed, true).findAll());
            }
        });
    }

    public Observable<List<TVShow>> getTrending() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                String filed = "isTrending";
                return realm.copyFromRealm(realm.where(TVShow.class).equalTo(filed, true).findAll());
            }
        });
    }

    public Observable<List<TVShow>> getByGenreID(int genreId) {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                List<TVShow> results = realm.where(TVShow.class).findAll();
                List<TVShow> data = new ArrayList<>();
                for (TVShow tvShow : results) {
                    if (tvShow.getGenres() != null) {
                        for (TVShowGenre tvShowGenre : tvShow.getGenres()) {
                            if (tvShowGenre.getId() == genreId) {
                                data.add(tvShow);
                                break;
                            }
                        }
                    }
                }
                return data;
            }
        });
    }
}
