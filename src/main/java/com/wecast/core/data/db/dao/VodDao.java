package com.wecast.core.data.db.dao;

import com.wecast.core.data.db.entities.Vod;
import com.wecast.core.data.db.entities.VodGenre;
import com.wecast.core.data.db.entities.VodSourceProfile;
import com.wecast.core.data.db.entities.VodSourceProfilePricing;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by ageech@live.com
 */

public class VodDao extends BaseDao<Vod> {

    private final Realm realm;

    public VodDao(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insert(final Vod data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public void insert(final List<Vod> data) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(data));
        }
    }

    @Override
    public Observable<List<Vod>> getAll() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                return realm.copyFromRealm(realm.where(Vod.class).findAll());
            }
        });
    }


    public Observable<List<Vod>> getRecommended() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                String filed = "isRecommended";
                return realm.copyFromRealm(realm.where(Vod.class).equalTo(filed, true).findAll());
            }
        });
    }

    public Observable<List<Vod>> getTrending() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                String filed = "isTrending";
                return realm.copyFromRealm(realm.where(Vod.class).equalTo(filed, true).findAll());
            }
        });
    }

    public Observable<List<Vod>> getContinueWatching() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                String filed = "isContinueWatching";
                return realm.copyFromRealm(realm.where(Vod.class).equalTo(filed, true).findAll());
            }
        });
    }

    @Override
    public Vod getById(final int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            Vod item = realm.where(Vod.class).equalTo("id", id).findFirst();
            if (item != null) {
                return realm.copyFromRealm(item);
            } else {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return (int) realm.where(Vod.class).count();
    }

    @Override
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(Vod.class));
        realm.close();
    }

    public VodSourceProfile getProfile(int vodId, int profileId) {
        Vod vod = getById(vodId);
        if (vod != null && vod.getMovieSource() != null && vod.getMovieSource().getProfiles() != null) {
            for (VodSourceProfile vodSourceProfile : vod.getMovieSource().getProfiles()) {
                if (vodSourceProfile.getId() == profileId) {
                    return vodSourceProfile;
                }
            }
        }
        return null;
    }

    public VodSourceProfilePricing getPricing(int vodId, int profileId, String pricingDate) {
        VodSourceProfile vodSourceProfile = getProfile(vodId, profileId);
        if (vodSourceProfile.getId() == profileId) {
            for (VodSourceProfilePricing pricing : vodSourceProfile.getPricing()) {
                if (pricing.getAvailableDate().equals(pricingDate)) {
                    return pricing;
                }
            }
        }
        return null;
    }

    public Observable<List<Vod>> getByGenreID(int genreId) {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                List<Vod> results = realm.where(Vod.class).findAll();
                List<Vod> data = new ArrayList<>();
                for (Vod vod : results) {
                    if (vod.getGenres() != null) {
                        for (VodGenre vodGenre : vod.getGenres()) {
                            if (vodGenre.getId() == genreId) {
                                data.add(vod);
                            }
                        }
                    }
                }
                return data;
            }
        });
    }
}
