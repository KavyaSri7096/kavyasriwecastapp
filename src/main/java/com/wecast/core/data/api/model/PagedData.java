package com.wecast.core.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ageech@live.com
 */

public class PagedData<T> {

    @SerializedName("items")
    private List<T> items;

    @SerializedName("pager")
    private PagerModel pager;

    public PagedData(List<T> items, PagerModel pager) {
        this.items = items;
        this.pager = pager;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public PagerModel getPager() {
        return pager;
    }

    public void setPager(PagerModel pager) {
        this.pager = pager;
    }
}