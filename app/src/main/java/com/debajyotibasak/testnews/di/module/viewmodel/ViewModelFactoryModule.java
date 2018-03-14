package com.debajyotibasak.testnews.di.module.viewmodel;

import android.content.Context;

import com.debajyotibasak.testnews.AppExecutors;
import com.debajyotibasak.testnews.db.NewsDataBase;
import com.debajyotibasak.testnews.di.interfaces.ApplicationContext;
import com.debajyotibasak.testnews.di.module.api.ContextModule;
import com.debajyotibasak.testnews.repo.NewsRepository;
import com.debajyotibasak.testnews.ui.MainActivityViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by debajyotibasak on 07/03/18.
 */

@Module(includes = ContextModule.class)
public class ViewModelFactoryModule {

    @Provides
    public NewsRepository provideRepository(NewsDataBase getDatabase, AppExecutors getAppExecutors) {
        return NewsRepository.getInstance(getDatabase.newsArticleDao(), getAppExecutors);
    }

    @Provides
    public NewsDataBase getDatabase(@ApplicationContext Context context) {
        return NewsDataBase.getInstance(context);
    }

    @Provides
    public AppExecutors getAppExecutors() {
        return AppExecutors.getInstance();
    }

    @Provides
    public MainActivityViewModelFactory provideMainActivityViewModelFactory(NewsRepository provideNewsRepository) {
        return new MainActivityViewModelFactory(provideNewsRepository);
    }

}