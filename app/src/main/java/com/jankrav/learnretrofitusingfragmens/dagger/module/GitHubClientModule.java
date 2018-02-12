package com.jankrav.learnretrofitusingfragmens.dagger.module;

import android.support.annotation.NonNull;

import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GitHubClientModule {

    @Provides
    @NonNull
    @Singleton
    public GitHubClient provideGitHubClient(){
        return GitHubClient.newInstance();
    }
}
