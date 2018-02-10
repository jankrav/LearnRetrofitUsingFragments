package com.jankrav.learnretrofitusingfragmens.dagger;


import com.jankrav.learnretrofitusingfragmens.dagger.module.AppModule;
import com.jankrav.learnretrofitusingfragmens.view.MainActivity;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
//    void inject(ChooseRepoFragment fragment);
//    void inject(DetailRepoFragment fragment);
}
