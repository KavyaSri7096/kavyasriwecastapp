package com.wecast.core.data.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ageech@live.com
 */

public class PagerModel {

    @SerializedName("finder")
    private String finder;

    @SerializedName("page")
    private int page;

    @SerializedName("current")
    private int current;

    @SerializedName("count")
    private int count;

    @SerializedName("perPage")
    private int perPage;

    @SerializedName("prevPage")
    private boolean prevPage;

    @SerializedName("nextPage")
    private boolean nextPage;

    @SerializedName("pageCount")
    private int pageCount;

    @SerializedName("sort")
    private String sort;

    @SerializedName("direction")
    private String direction;

    @SerializedName("limit")
    private int limit;

    @SerializedName("sortDefault")
    private String sortDefault;

    @SerializedName("directionDefault")
    private String directionDefault;

    public PagerModel(int page, int count) {
        this.page = page;
        this.count = count;
    }

    public String getFinder() {
        return finder;
    }

    public void setFinder(String finder) {
        this.finder = finder;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public boolean getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(boolean prevPage) {
        this.prevPage = prevPage;
    }

    public boolean getNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSortDefault() {
        return sortDefault;
    }

    public void setSortDefault(String sortDefault) {
        this.sortDefault = sortDefault;
    }

    public String getDirectionDefault() {
        return directionDefault;
    }

    public void setDirectionDefault(String directionDefault) {
        this.directionDefault = directionDefault;
    }
}
