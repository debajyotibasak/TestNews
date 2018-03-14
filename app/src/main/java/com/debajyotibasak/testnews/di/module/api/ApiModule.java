package com.debajyotibasak.testnews.di.module.api;

import com.debajyotibasak.testnews.api.NewsSourceService;
import com.debajyotibasak.testnews.utils.LiveDataCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by debajyotibasak on 12/03/18.
 */

@Module(includes = OkHttpClientModule.class)
public class ApiModule {

    public static final String BASE_URL = "https://newsapi.org/v1/";

    @Provides
    @Singleton
    public NewsSourceService newsSourceService(Retrofit retrofit) {
        return retrofit.create(NewsSourceService.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory,
                             LiveDataCallAdapterFactory liveDataCallAdapterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(liveDataCallAdapterFactory)
                .build();
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    public LiveDataCallAdapterFactory liveDataCallAdapterFactory() {
        return new LiveDataCallAdapterFactory();
    }

}
