package com.wecast.core.data.api;

import android.os.Build;
import android.text.TextUtils;

import com.wecast.core.WeCore;
import com.wecast.core.data.db.pref.PreferenceManager;
import com.wecast.core.utils.HashUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ageech@live.com
 */

public class ApiInterceptor implements Interceptor {

    private static final String QUERY_USERNAME = "username";
    private static final String QUERY_PASSWORD = "password";
    private static final String QUERY_TOKEN = "token";

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_UID = "UID";
    private static final String HEADER_DEVICE = "Device";
    private static final String HEADER_DEVICE_MODEL = "Device-Model";
    private static final String HEADER_APP_VERSION = "App-Version";
    private static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    private static final String HEADER_TOKEN = "token";

    private String manufacturer;

    private PreferenceManager preferenceManager;

    @Inject
    public ApiInterceptor(PreferenceManager preferenceManager) {
        this.preferenceManager = preferenceManager;
    }

    @NonNull
    @Override
    public Response intercept(@NotNull @NonNull Chain chain) throws IOException {
        Request request = chain.request();


        String username = request.url().queryParameter(QUERY_USERNAME);
        String password = request.url().queryParameter(QUERY_PASSWORD);
        String token = request.url().queryParameter(QUERY_TOKEN);

        if (manufacturer == null) {
            String string = Build.MANUFACTURER;
            manufacturer = string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
        }

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            HttpUrl url = request.url().newBuilder()
                    .removeAllQueryParameters(QUERY_USERNAME)
                    .removeAllEncodedQueryParameters(QUERY_PASSWORD)
                    .build();

            request = request.newBuilder()
                    .url(url)
                    .addHeader(HEADER_AUTHORIZATION, String.format("%s %s", "Basic", HashUtils.getBasicAuthDigest(username, password)))
                    .addHeader(HEADER_UID, WeCore.getApplicationId())
                    .addHeader(HEADER_DEVICE, WeCore.getApplicationType())
                    .addHeader(HEADER_DEVICE_MODEL, manufacturer + " " + Build.MODEL)
                    .addHeader(HEADER_APP_VERSION, WeCore.getVersionName())
                    .addHeader(HEADER_ACCEPT_LANGUAGE, preferenceManager.getLanguage().getShortCode())
                    .build();
        } else if (!TextUtils.isEmpty(token)) {
            HttpUrl url = request.url().newBuilder()
                    .removeAllQueryParameters(QUERY_TOKEN)
                    .build();

            request = request.newBuilder()
                    .url(url)
                    .addHeader(HEADER_TOKEN, token)
                    .addHeader(HEADER_UID, WeCore.getApplicationId())
                    .addHeader(HEADER_DEVICE, WeCore.getApplicationType())
                    .addHeader(HEADER_DEVICE_MODEL, manufacturer + " " + Build.MODEL)
                    .addHeader(HEADER_ACCEPT_LANGUAGE, preferenceManager.getLanguage().getShortCode())
                    .build();
        } else {
            String localToken = preferenceManager.getAccessToken();

            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader(HEADER_UID, WeCore.getApplicationId())
                    .addHeader(HEADER_DEVICE, WeCore.getApplicationType())
                    .addHeader(HEADER_DEVICE_MODEL, manufacturer + " " + Build.MODEL)
                    .addHeader(HEADER_ACCEPT_LANGUAGE, preferenceManager.getLanguage().getShortCode());

            if (!TextUtils.isEmpty(localToken)) {
                requestBuilder = requestBuilder.addHeader(HEADER_TOKEN, localToken);
            }

            request = requestBuilder.build();
        }

        return chain.proceed(request);
    }
}