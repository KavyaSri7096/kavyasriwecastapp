package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.composer.Composer;

import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

public class ComposerDao {

    private final Realm realm;

    public ComposerDao(Realm realm) {
        this.realm = realm;
    }

    public void insert(final Composer data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    public Composer getComposer() {
        try (Realm realm = Realm.getDefaultInstance()) {
            Composer item = realm.where(Composer.class).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return null;
            }
        }
    }

    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(Composer.class));
        realm.close();
    }
}
