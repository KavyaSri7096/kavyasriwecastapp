package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class ParentalRating extends RealmObject {

    @SerializedName("code")
    private String code;

    @SerializedName("require_pin")
    private boolean requirePin;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isRequirePin() {
        return requirePin;
    }

    public void setRequirePin(boolean requirePin) {
        this.requirePin = requirePin;
    }
}
