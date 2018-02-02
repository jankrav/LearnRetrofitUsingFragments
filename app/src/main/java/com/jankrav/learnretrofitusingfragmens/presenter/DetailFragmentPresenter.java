package com.jankrav.learnretrofitusingfragmens.presenter;

import android.support.annotation.NonNull;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.DetailGitHubClient;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public class DetailFragmentPresenter implements DetailPresenter {
    private DetailGitHubClient client = new GitHubClient();
    private DetailFragmentView view;

    public DetailFragmentPresenter() {
    }

//    for testing
    @Override
    public void setClient(DetailGitHubClient client) {
        this.client = client;
    }

    @Override
    public void onAttachView(DetailFragmentView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        view = null;
    }

    @Override
    public void onSelectedRepo(String repoOwnerLogin, String repoName) {
        client.getRepoInfo(this, repoOwnerLogin, repoName);
    }

    @Override
    public void requestResponse(GitHubRepo repo) {
        if (repo != null ){
            view.showInfo(repo);
            view.makeGoodToast();
        }
        else view.makeRepoIsNullToast();
    }

    @Override
    public void requestFailure(Throwable t) {
        view.makeFailureToast();
    }
}
