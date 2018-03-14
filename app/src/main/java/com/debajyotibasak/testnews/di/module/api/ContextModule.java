package com.debajyotibasak.testnews.di.module.api;

import android.content.Context;

import com.debajyotibasak.testnews.di.interfaces.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by debajyotibasak on 06/03/18.
 */

@Module
public class ContextModule {
    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @ApplicationContext
    @Provides
    @Singleton
    public Context context() {
        return context.getApplicationContext();
    }
}
