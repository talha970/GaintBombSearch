package com.example.tjaved.testapp.di.components;

import com.example.tjaved.testapp.App;
import com.example.tjaved.testapp.di.modules.AppModule;
import com.example.tjaved.testapp.di.modules.NetModule;
import com.example.tjaved.testapp.views.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tjaved on 3/24/18.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(App app);

    void inject(MainActivity mainActivity);

}
