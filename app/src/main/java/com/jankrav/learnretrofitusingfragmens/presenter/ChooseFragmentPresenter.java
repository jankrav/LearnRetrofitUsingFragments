package com.jankrav.learnretrofitusingfragmens.presenter;

import android.support.annotation.NonNull;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.ChooseGitHubClient;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

import java.util.List;

public class ChooseFragmentPresenter implements ChoosePresenter {
    private ChooseGitHubClient client = new GitHubClient();

    private ChooseFragmentView view;

    public ChooseFragmentPresenter() {
    }

    @Override
    public void onUserChosen(String user) {
        client.getReposForUser(this, user);
    }


    @Override
     public void onAttachView(ChooseFragmentView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        view = null;
    }

    @Override
    public void onSelectedRepo(String repoOwnerLogin, String repoName) {
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
