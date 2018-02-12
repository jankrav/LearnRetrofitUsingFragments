package com.jankrav.learnretrofitusingfragmens.dagger.module;

import android.support.annotation.NonNull;

import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.presenter.DetailFragmentPresenter;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailPresenterModule {

    @Provides
    @NonNull
    @Singleton
    public DetailFragmentPresenter provideDetailPresenter(DetailFragmentView view, GitHubClient client){
        return new DetailFragmentPresenter(view, client);
    }
}
