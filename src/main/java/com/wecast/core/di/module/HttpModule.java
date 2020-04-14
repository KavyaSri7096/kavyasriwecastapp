package com.wecast.core.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wecast.core.BuildConfig;
import com.wecast.core.WeCore;
import com.wecast.core.data.api.ApiInterceptor;
import com.wecast.core.data.db.pref.PreferenceManager;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ageech@live.com
 */

@Module
public class HttpModule {

    private static final String HEADER_TENANT = "Tenant";
    private static final String HEADER_TENANT_DEFAULT = "general";

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    ApiInterceptor provideApiInterceptor(PreferenceManager preferenceManager) {
        return new ApiInterceptor(preferenceManager);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    @Named("Secured")
    OkHttpClient provideSecuredOkHttpClient(HttpLoggingInterceptor loggingInterceptor, ApiInterceptor apiInterceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(30L, TimeUnit.SECONDS);
        okHttpClient.readTimeout(30L, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(30L, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(apiInterceptor);
        okHttpClient.addInterceptor(loggingInterceptor);
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest;
                newRequest = request.newBuilder()
                        .addHeader(HEADER_TENANT, HEADER_TENANT_DEFAULT)
                        .build();
                return chain.proceed(newRequest);
            }
        });
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitClient(@Named("Secured") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(WeCore.getApiUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * To avoid SSL exception,
     * we have to use unsafe OkHttpClient
     */

    @Provides
    @Singleton
    @Named("Unsecured")
    OkHttpClient provideUnSecuredOkHttpClient(X509TrustManager x509TrustManager, HttpLoggingInterceptor loggingInterceptor, ApiInterceptor apiInterceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{x509TrustManager};
            // Install the all-trusting trust manager 
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager 
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            okHttpClient.sslSocketFactory(sslSocketFactory);
            okHttpClient.hostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        okHttpClient.connectTimeout(30L, TimeUnit.SECONDS);
        okHttpClient.readTimeout(30L, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(30L, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(apiInterceptor);
        okHttpClient.addInterceptor(loggingInterceptor);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    X509TrustManager provideX509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }
}
