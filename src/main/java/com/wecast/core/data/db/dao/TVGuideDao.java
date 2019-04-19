package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.TVGuide;
import com.wecast.core.data.db.entities.TVGuideProgramme;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

public class TVGuideDao extends BaseDao<TVGuide> {

    private final Realm realm;

    public TVGuideDao(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insert(final TVGuide data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public void insert(final List<TVGuide> data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public Observable<List<TVGuide>> getAll() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(TVGuide.class).findAll());
            }
        });
    }

    @Override
    public TVGuide getById(final int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            TVGuide item = realm.where(TVGuide.class).equalTo("id", id).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return null;
            }
        }
    }

    public Observable<TVGuide> getProgrammes(int id) {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                List<TVGuide> items = realm.where(TVGuide.class).findAll();
                for (TVGuide tvGuide : items) {
                    if (tvGuide.getId() == id) {
                        return realm.copyFromRealm(tvGuide);
                    }
                }
                return new TVGuide();
            }
        });
    }

    public TVGuideProgramme getProgrammeById(final String stringId) {
        try (Realm realm = Realm.getDefaultInstance()) {
            List<TVGuide> items = realm.where(TVGuide.class).findAll();
            for (TVGuide tvGuide : items) {
                for (TVGuideProgramme tvGuideProgramme : tvGuide.getProgrammes()) {
                    if (tvGuideProgramme.getStringId().equals(stringId)) {
                        return realm.copyFromRealm(tvGuideProgramme);
                    }
                }
            }
            return null;
        }
    }

    @Override
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(TVGuide.class));
        realm.close();
    }

    @Override
    public int getCount() {
        return (int) realm.where(TVGuide.class).count();
    }
}
