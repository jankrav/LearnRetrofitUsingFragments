package com.jankrav.learnretrofitusingfragmens.dagger;


import com.jankrav.learnretrofitusingfragmens.dagger.module.AppModule;
import com.jankrav.learnretrofitusingfragmens.dagger.module.GitHubClientModule;
import com.jankrav.learnretrofitusingfragmens.presenter.ChooseFragmentPresenter;
import com.jankrav.learnretrofitusingfragmens.presenter.DetailFragmentPresenter;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        GitHubClientModule.class})

public interface AppComponent {
    void inject(ChooseFragmentPresenter presenter);
    void inject(DetailFragmentPresenter presenter);
}
