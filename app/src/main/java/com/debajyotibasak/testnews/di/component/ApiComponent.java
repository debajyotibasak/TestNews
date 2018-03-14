package com.debajyotibasak.testnews.di.component;

import com.debajyotibasak.testnews.di.module.api.ApiModule;
import com.debajyotibasak.testnews.repo.NewsRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by debajyotibasak on 12/03/18.
 */

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {
    /*ApiInterface apiInterface();*/
    void injectRepository(NewsRepository repository);
}
