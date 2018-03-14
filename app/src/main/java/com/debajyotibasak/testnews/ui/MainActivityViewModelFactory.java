package com.debajyotibasak.testnews.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.debajyotibasak.testnews.repo.NewsRepository;

/**
 * Created by debajyotibasak on 12/03/18.
 */

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    NewsRepository mRepository;

    public MainActivityViewModelFactory(NewsRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}

