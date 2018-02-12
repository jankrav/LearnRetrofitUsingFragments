package com.jankrav.learnretrofitusingfragmens.dagger;


import com.jankrav.learnretrofitusingfragmens.dagger.module.AppModule;
import com.jankrav.learnretrofitusingfragmens.dagger.module.ChooseFragmentModule;
import com.jankrav.learnretrofitusingfragmens.dagger.module.ChoosePresenterModule;
import com.jankrav.learnretrofitusingfragmens.dagger.module.DetailFragmentModule;
import com.jankrav.learnretrofitusingfragmens.dagger.module.DetailPresenterModule;
import com.jankrav.learnretrofitusingfragmens.dagger.module.GitHubClientModule;
import com.jankrav.learnretrofitusingfragmens.view.MainActivity;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailRepoFragment;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        ChooseFragmentModule.class,
        ChoosePresenterModule.class,
        DetailFragmentModule.class,
        DetailPresenterModule.class,
        GitHubClientModule.class})

public interface AppComponent {
    void inject(MainActivity activity);

    void inject(ChooseRepoFragment fragment);

    void inject(DetailRepoFragment fragment);
}
