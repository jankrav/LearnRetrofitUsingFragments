package com.jankrav.learnretrofitusingfragmens.dagger;

import android.app.Application;

import com.jankrav.learnretrofitusingfragmens.dagger.module.AppModule;
import com.jankrav.learnretrofitusingfragmens.dagger.module.ChooseFragmentModule;
import com.jankrav.learnretrofitusingfragmens.dagger.module.DetailFragmentModule;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailRepoFragment;

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
                .chooseFragmentModule(new ChooseFragmentModule(ChooseRepoFragment.newInstance()))
                .detailFragmentModule(new DetailFragmentModule(DetailRepoFragment.newInstance()))
                .build();
    }
}
