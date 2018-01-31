package com.jankrav.learnretrofitusingfragmens.presenter;

import android.support.annotation.VisibleForTesting;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

import java.util.List;

public class ChooseFragmentPresenter implements ChoosePresenter {
    private GitHubClient client = GitHubClient.getInstance();
    @VisibleForTesting
    private ChooseFragmentView view;

    public ChooseFragmentPresenter(ChooseFragmentView view) {
        this.view = view;
    }

    @Override
    public void onUserChosen(String user)  {
        if(user != null)
            client.getReposForUser(this, user);
        else throw new NullPointerException("User's login is equals null");
    }

    @Override
    public void onSelectedRepo(String repoOwnerLogin, String repoName) {
        view.checkoutToDetailFragment(repoOwnerLogin, repoName);
    }

    @Override
    public void requestResponse(List<GitHubRepo> repos) {
        if(repos != null) {
            view.showInfo(repos);
            view.makeGoodToast();
        }
        else view.makeReposIsNullToast();
    }

    @Override
    public void requestFailure(Throwable t) {
        view.makeFailureToast();
    }

}
