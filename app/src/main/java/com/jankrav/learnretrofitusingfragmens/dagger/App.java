package com.jankrav.learnretrofitusingfragmens.dagger;

import android.app.Application;

import com.jankrav.learnretrofitusingfragmens.dagger.module.AppModule;

public class App extends Application {
    private AppComponent component;

    public AppComponent getComponent(){
        return component;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
