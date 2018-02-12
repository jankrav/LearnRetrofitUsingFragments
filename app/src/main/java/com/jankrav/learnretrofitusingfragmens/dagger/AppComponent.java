package com.jankrav.learnretrofitusingfragmens.dagger;


import com.jankrav.learnretrofitusingfragmens.dagger.module.GitHubClientModule;
import com.jankrav.learnretrofitusingfragmens.presenter.ChooseFragmentPresenter;
import com.jankrav.learnretrofitusingfragmens.presenter.DetailFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {GitHubClientModule.class})

@Singleton
public interface AppComponent {
    void inject(ChooseFragmentPresenter presenter);
    void inject(DetailFragmentPresenter presenter);
}
