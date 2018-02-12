package com.jankrav.learnretrofitusingfragmens.dagger;


import com.jankrav.learnretrofitusingfragmens.dagger.module.AppModule;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailRepoFragment;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(ChooseRepoFragment fragment);

    void inject(DetailRepoFragment fragment);
}
