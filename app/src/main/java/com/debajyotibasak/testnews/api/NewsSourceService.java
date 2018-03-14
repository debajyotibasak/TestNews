package com.debajyotibasak.testnews.api;

import android.arch.lifecycle.LiveData;

import com.debajyotibasak.testnews.BuildConfig;
import com.debajyotibasak.testnews.model.NewsSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by debajyotibasak on 12/03/18.
 */

public interface NewsSourceService {

    @GET("articles?source=google-news&apiKey=" + BuildConfig.NEWS_API_KEY)
    LiveData<ApiResponse<NewsSource>> getNewsSource();

}
