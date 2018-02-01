package com.jankrav.learnretrofitusingfragmens.model.client;


import com.jankrav.learnretrofitusingfragmens.presenter.DetailPresenter;

interface DetailGitHubClient {
    void getRepoInfo(DetailPresenter presenter, String repoOwnerLogin, String repoName);
}
