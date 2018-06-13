package com.debajyotibasak.testnews.repo;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.debajyotibasak.testnews.AppExecutors;
import com.debajyotibasak.testnews.api.ApiResponse;
import com.debajyotibasak.testnews.api.NewsSourceService;
import com.debajyotibasak.testnews.db.NewsArticleDao;
import com.debajyotibasak.testnews.di.component.ApiComponent;
import com.debajyotibasak.testnews.di.component.DaggerApiComponent;
import com.debajyotibasak.testnews.model.NewsArticle;
import com.debajyotibasak.testnews.model.NewsSource;
import com.debajyotibasak.testnews.model.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by debajyotibasak on 12/03/18.
 */

public class NewsRepository {
    private final NewsArticleDao newsDao;
    private final AppExecutors appExecutors;

    private static final Object LOCK = new Object();
    private static NewsRepository sInstance;

    @Inject
    NewsSourceService newsSourceService;

    public NewsRepository(NewsArticleDao dao, AppExecutors executors) {
        ApiComponent daggerAppComponent = DaggerApiComponent.builder().build();
        daggerAppComponent.injectRepository(this);
        newsDao = dao;
        appExecutors = executors;
    }

    public synchronized static NewsRepository getInstance(NewsArticleDao newsDao, AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NewsRepository(newsDao, executors);
            }
        }
        return sInstance;
    }

    public LiveData<Resource<List<NewsArticle>>> loadNews() {
        return new NetworkBoundResource<List<NewsArticle>, NewsSource>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull NewsSource item) {
                newsDao.insertArticles(item.getArticles());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<NewsArticle> data) {
                return data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<NewsArticle>> loadFromDb() {
                return newsDao.getNewsArticles();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<NewsSource>> createCall() {
                return newsSourceService.getNewsSource();
            }

        }.asLiveData();
    }
}
