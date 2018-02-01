package com.jankrav.learnretrofitusingfragmens.presenter;

import android.support.annotation.NonNull;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public class DetailFragmentPresenter implements DetailPresenter {
    private GitHubClient client = GitHubClient.getInstance();
    private DetailFragmentView view;

    public DetailFragmentPresenter() {
    }

    @Override
    public void onAttachView(DetailFragmentView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
//        view = null;
    }

    @Override
    public void onSelectedRepo(@NonNull String repoOwnerLogin, @NonNull String repoName) {
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
