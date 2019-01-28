package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ageech@live.com
 */

public class VodImage extends RealmObject {

    @SerializedName("original")
    private String original;

    @SerializedName("preview")
    private String preview;

    @SerializedName("preview_ar")
    private String previewAr;

    @SerializedName("small")
    private String small;

    @SerializedName("small_ar")
    private String smallAr;

    @SerializedName("medium")
    private String medium;

    @SerializedName("medium_ar")
    private String mediumAr;

    @SerializedName("cover_small")
    private String coverSmall;

    @SerializedName("cover_preview")
    private String coverPreview;

    @SerializedName("preview_small")
    private String previewSmall;

    @SerializedName("preview_small_ar")
    private String previewSmallAr;

    @SerializedName("preview_medium")
    private String previewMedium;

    @SerializedName("preview_medium_ar")
    private String previewMediumAr;

    @SerializedName("preview_large")
    private String previewLarge;

    @SerializedName("preview_large_ar")
    private String previewLargeAr;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getPreviewAr() {
        return previewAr;
    }

    public void setPreviewAr(String previewAr) {
        this.previewAr = previewAr;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getSmallAr() {
        return smallAr;
    }

    public void setSmallAr(String smallAr) {
        this.smallAr = smallAr;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getMediumAr() {
        return mediumAr;
    }

    public void setMediumAr(String mediumAr) {
        this.mediumAr = mediumAr;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getCoverPreview() {
        return coverPreview;
    }

    public void setCoverPreview(String coverPreview) {
        this.coverPreview = coverPreview;
    }

    public String getPreviewSmall() {
        return previewSmall;
    }

    public void setPreviewSmall(String previewSmall) {
        this.previewSmall = previewSmall;
    }

    public String getPreviewSmallAr() {
        return previewSmallAr;
    }

    public void setPreviewSmallAr(String previewSmallAr) {
        this.previewSmallAr = previewSmallAr;
    }

    public String getPreviewMedium() {
        return previewMedium;
    }

    public void setPreviewMedium(String previewMedium) {
        this.previewMedium = previewMedium;
    }

    public String getPreviewMediumAr() {
        return previewMediumAr;
    }

    public void setPreviewMediumAr(String previewMediumAr) {
        this.previewMediumAr = previewMediumAr;
    }

    public String getPreviewLarge() {
        return previewLarge;
    }

    public void setPreviewLarge(String previewLarge) {
        this.previewLarge = previewLarge;
    }

    public String getPreviewLargeAr() {
        return previewLargeAr;
    }

    public void setPreviewLargeAr(String previewLargeAr) {
        this.previewLargeAr = previewLargeAr;
    }
}