package com.wecast.core.data.db.entities;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class VodSourceProfilePricing extends RealmObject {

    @SerializedName("name")
    private String name;

    @SerializedName("available_date")
    private String availableDate;

    @SerializedName("duration")
    private String duration;

    @SerializedName("price")
    private String price;

    @SerializedName("promo_price")
    private String promoPrice;

    @SerializedName("promo_price_from")
    private String promoPriceFrom;

    @SerializedName("promo_price_to")
    private String promoPriceTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(String promoPrice) {
        this.promoPrice = promoPrice;
    }

    public String getPromoPriceFrom() {
        return promoPriceFrom;
    }

    public void setPromoPriceFrom(String promoPriceFrom) {
        this.promoPriceFrom = promoPriceFrom;
    }

    public String getPromoPriceTo() {
        return promoPriceTo;
    }

    public void setPromoPriceTo(String promoPriceTo) {
        this.promoPriceTo = promoPriceTo;
    }

    public boolean isAvailable() {
        if (TextUtils.isEmpty(availableDate))
            return true;

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
        try {
            Date date = format.parse(availableDate);
            long now = System.currentTimeMillis();
            return (now > date.getTime());
        } catch (Exception ex) {
            return true;
        }
    }

    public String getCalculatedPrice() {
        if (TextUtils.isEmpty(promoPriceFrom) || TextUtils.isEmpty(promoPriceTo)) {
            return price;
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
        try {
            Date promoStart = format.parse(promoPriceFrom);
            Date promoEnd = format.parse(promoPriceTo);
            long now = System.currentTimeMillis();
            return (now > promoStart.getTime() && now < promoEnd.getTime()) ? promoPrice : price;
        } catch (Exception ex) {
            return price;
        }
    }
}
