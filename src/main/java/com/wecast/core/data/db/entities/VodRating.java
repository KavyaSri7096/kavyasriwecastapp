package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class VodRating extends RealmObject {

    @SerializedName("rateAverage")
    private double rateAverage;

    @SerializedName("numberOfRates")
    private double numberOfRates;

    public double getRateAverage() {
        return rateAverage;
    }

    public void setRateAverage(double rateAverage) {
        this.rateAverage = rateAverage;
    }

    public double getNumberOfRates() {
        return numberOfRates;
    }

    public void setNumberOfRates(double numberOfRates) {
        this.numberOfRates = numberOfRates;
    }
}
