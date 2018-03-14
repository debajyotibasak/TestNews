package com.debajyotibasak.testnews.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.debajyotibasak.testnews.model.NewsArticle;
import com.debajyotibasak.testnews.model.Resource;
import com.debajyotibasak.testnews.repo.NewsRepository;

import java.util.List;

/**
 * Created by debajyotibasak on 11/03/18.
 */

public class MainActivityViewModel extends ViewModel{

    private LiveData<Resource<List<NewsArticle>>> mNews;

    MainActivityViewModel(NewsRepository newsRepository) {
        mNews = newsRepository.loadNews();
    }

    public LiveData<Resource<List<NewsArticle>>> getNewsArticles(){
        return mNews;
    }
}