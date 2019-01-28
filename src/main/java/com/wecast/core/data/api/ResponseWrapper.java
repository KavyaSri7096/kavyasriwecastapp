package com.wecast.core.data.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by ageech@live.com
 */

public class ResponseWrapper<T> {

    @NonNull
    public ApiStatus status;

    @Nullable
    public T data;

    @Nullable
    public String message;

    private ResponseWrapper(@NonNull ApiStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public ResponseWrapper(@NonNull ApiStatus status, @Nullable T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseWrapper(@NonNull ApiStatus status, @Nullable String message) {
        this.status = status;
        this.message = message;
    }

    private ResponseWrapper(@NonNull ApiStatus status) {
        this.status = status;
    }

    public static <T> ResponseWrapper<T> loading() {
        return new ResponseWrapper<>(ApiStatus.LOADING);
    }

    public static <T> ResponseWrapper<T> loading(@Nullable T data) {
        return new ResponseWrapper<>(ApiStatus.LOADING, data);
    }

    public static <T> ResponseWrapper<T> success(@NonNull T data) {
        return new ResponseWrapper<>(ApiStatus.SUCCESS, data, null);
    }

    public static <T> ResponseWrapper<T> error(String msg) {
        return new ResponseWrapper<>(ApiStatus.ERROR, msg);
    }

    public static <T> ResponseWrapper<T> tokenExpired() {
        return new ResponseWrapper<>(ApiStatus.TOKEN_EXPIRED);
    }

    public static <T> ResponseWrapper<T> subscriptionExpired(@Nullable T data) {
        return new ResponseWrapper<>(ApiStatus.SUBSCRIPTION_EXPIRED, data);
    }
}