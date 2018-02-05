package com.jankrav.learnretrofitusingfragmens.presenter;

import android.text.TextUtils;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

import java.util.List;

public class ChooseFragmentPresenter implements ChoosePresenter, GitHubClient.OnChooserDataLoadedListener{
    private GitHubClient client = GitHubClient.newInstance();

    private ChooseFragmentView view;

    public ChooseFragmentPresenter() {

    }

    //For testing
    @Override
    public void setClient(GitHubClient client) {
        this.client = client;
    }

    @Override
    public void onUserChosen(String user) {
        client.getReposForUser(user, this);
    }

    @Override
    public void onResponse(List<GitHubRepo> repos) {
        if (repos != null) {
            view.showInfo(repos);
            view.makeGoodToast();
        } else view.makeReposIsNullToast();
    }

    @Override
    public void onFailure(Throwable t) {
        view.makeFailureToast();
    }

    @Override
    public void onError() {
        view.makeToast("User login is empty");
    }

    @Override
    public void onSelectedRepo(String repoOwnerLogin, String repoName) {
        if (!TextUtils.isEmpty(repoOwnerLogin) && !TextUtils.isEmpty(repoName))
            view.checkoutToDetailFragment(repoOwnerLogin, repoName);
        else view.makeUserInfoFailureToast();
    }



    @Override
    public void onAttachView(ChooseFragmentView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        view = null;
    }
}
