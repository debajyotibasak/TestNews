package com.debajyotibasak.testnews.di.component;

import com.debajyotibasak.testnews.di.module.viewmodel.ViewModelFactoryModule;
import com.debajyotibasak.testnews.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by debajyotibasak on 12/03/18.
 */

@Singleton
@Component(modules = ViewModelFactoryModule.class)
public interface ViewModelFactoryComponent {

    void inject(MainActivity activity);
}
