package com.jankrav.learnretrofitusingfragmens.dagger.module;

import android.support.annotation.NonNull;

import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.presenter.ChooseFragmentPresenter;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ChoosePresenterModule {

    @Provides
    @NonNull
    @Singleton
    public ChooseFragmentPresenter provideChoosePresenter(ChooseFragmentView view, GitHubClient client){
        return new ChooseFragmentPresenter(view, client);
    }
}
