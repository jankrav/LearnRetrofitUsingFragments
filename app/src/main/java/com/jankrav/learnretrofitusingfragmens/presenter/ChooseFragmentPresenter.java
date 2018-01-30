package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

import java.util.List;

public class ChooseFragmentPresenter implements ChoosePresenter {
    private ChooseFragmentView view;
    private GitHubClient client = GitHubClient.getInstance();

    public ChooseFragmentPresenter(ChooseFragmentView view) {
        this.view = view;
    }

    @Override
    public void onSelectedRepo(int id) {
        view.checkoutToDetailFragment(id);
    }

    @Override
    public void onUserChosen(String user) {
        client.getReposForUser(this, user);
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
