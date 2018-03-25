package com.example.tjaved.testapp;

import android.app.Application;

import com.example.tjaved.testapp.di.components.AppComponent;
import com.example.tjaved.testapp.di.components.DaggerAppComponent;
import com.example.tjaved.testapp.di.modules.AppModule;
import com.example.tjaved.testapp.di.modules.NetModule;

/**
 * Created by tjaved on 3/24/18.
 */

public class App extends Application {
    private static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        setUpComponent();
    }

    public void setUpComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .netModule(new NetModule(getResources().getString(R.string.BASE_URL)))
                    .build();
        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
