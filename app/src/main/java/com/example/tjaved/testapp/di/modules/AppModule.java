package com.example.tjaved.testapp.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tjaved on 3/24/18.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application app) {
        this.application = app;
    }


    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }
}

