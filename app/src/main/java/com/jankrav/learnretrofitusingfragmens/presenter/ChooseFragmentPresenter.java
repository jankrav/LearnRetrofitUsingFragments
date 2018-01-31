package com.jankrav.learnretrofitusingfragmens.presenter;

import android.support.annotation.NonNull;
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
    public void onUserChosen(String user) {
        client.getReposForUser(this, user);
    }

    @Override
    public void onSelectedRepo(@NonNull String repoOwnerLogin, @NonNull String repoName) {
        if (!repoOwnerLogin.equals("") && !repoName.equals(""))
            view.checkoutToDetailFragment(repoOwnerLogin, repoName);
        else throw new NullPointerException();
    }

    @Override
    public void requestResponse(List<GitHubRepo> repos) {
        if (repos != null) {
            view.showInfo(repos);
            view.makeGoodToast();
        } else view.makeReposIsNullToast();
    }

    @Override
    public void requestFailure(Throwable t) {
        view.makeFailureToast();
    }

}
