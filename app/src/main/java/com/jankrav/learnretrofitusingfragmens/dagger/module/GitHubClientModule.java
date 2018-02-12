package com.jankrav.learnretrofitusingfragmens.dagger.module;

import android.support.annotation.NonNull;

import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubService;
import com.jankrav.learnretrofitusingfragmens.model.client.ServiceGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GitHubClientModule {

    @Provides
    @NonNull
    @Singleton
    public GitHubClient provideGitHubClient(GitHubService service){
        return new GitHubClient(service);
    }

    @Provides
    @NonNull
    @Singleton
    public GitHubService provideGitHubService(){
        return new ServiceGenerator().getDefaultService();
    }

}
