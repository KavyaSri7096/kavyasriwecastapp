package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.TVGuideReminder;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ageech@live.com
 */

public class ReminderDao extends BaseDao<TVGuideReminder> {

    private final Realm realm;

    public ReminderDao(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insert(TVGuideReminder data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public void insert(List<TVGuideReminder> data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public Observable<List<TVGuideReminder>> getAll() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(TVGuideReminder.class).findAll());
            }
        });
    }

    @Override
    public TVGuideReminder getById(int id) {
        return null;
    }

    public TVGuideReminder getByStringId(String stringId) {
        try (Realm realm = Realm.getDefaultInstance()) {
            TVGuideReminder item = realm.where(TVGuideReminder.class).equalTo("stringId", stringId).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return new TVGuideReminder();
            }
        }
    }

    public void remove(int id) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            RealmResults<TVGuideReminder> item = realmInstance.where(TVGuideReminder.class).equalTo("id", id).findAll();
            realmInstance.executeTransaction(realm -> item.deleteAllFromRealm());
        }
    }

    @Override
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(TVGuideReminder.class));
        realm.close();
    }

    @Override
    public int getCount() {
        return (int) realm.where(TVGuideReminder.class).count();
    }
}
