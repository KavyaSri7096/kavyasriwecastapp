package com.wecast.core.data.db.dao;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */

public abstract class BaseDao<T> {

    public abstract void insert(final T data);

    public abstract void insert(final List<T> data);

    public abstract Observable<List<T>> getAll();

    public abstract T getById(final int id);

    public abstract void clear();

    public abstract int getCount();
}
