package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public class DetailFragmentPresenter implements DetailPresenter {
    private GitHubClient client = GitHubClient.getInstance();
    private DetailFragmentView view;

    public DetailFragmentPresenter(DetailFragmentView view) {
        this.view = view;
    }

    @Override
    public void onSelectedRepo(int id) {
        client.getRepoInfo(this, id);
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

    }
}
