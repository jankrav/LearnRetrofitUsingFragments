package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

import java.util.List;

public class ChooseFragmentPresenter implements ChoosePresenter, GitHubClient.OnChooserDataLoadedListener {
    private GitHubClient client;
    private ChooseFragmentView view;

    public ChooseFragmentPresenter(GitHubClient client) {
        this.client = client;
    }

    // attach detach view to the presenter
    @Override
    public void onAttachView(ChooseFragmentView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        view = null;
    }

    // view event's
    @Override
    public void onUserChosen(String user) {
        if (user != null && !user.equals("")) {
            client.getReposForUser(user, this);
        } else {
            view.makeUserLoginIsNullToast();
        }
    }

    @Override
    public void onSelectedRepo(String repoOwnerLogin, String repoName) {
        if ((repoOwnerLogin != null && !repoOwnerLogin.equals("")) &&
                (repoName != null && !repoName.equals(""))) {
            view.checkoutToDetailFragment(repoOwnerLogin, repoName);
        } else {
            view.makeUserInfoFailureToast();
        }
    }

    // model event's
    @Override
    public void onResponse(List<GitHubRepo> repos) {
        if (repos != null) {
            view.showInfo(repos);
            view.makeGoodToast();
        } else {
            view.makeReposIsNullToast();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        view.makeFailureToast();
    }

    @Override
    public void setView(ChooseFragmentView view) {
        this.view = view;
    }

    @Override
    public ChooseFragmentView getView() {
        return view;
    }
}
