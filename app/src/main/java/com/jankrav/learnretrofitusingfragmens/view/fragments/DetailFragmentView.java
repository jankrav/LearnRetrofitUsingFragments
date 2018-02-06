package com.jankrav.learnretrofitusingfragmens.view.fragments;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.presenter.DetailPresenter;

public interface DetailFragmentView {
    void setPresenter(DetailPresenter presenter);

    void showInfo(GitHubRepo repo);

    void makeFailureToast();

    void makeGoodToast();

    void makeRepoIsNullToast();

    void makeUserInfoFailureToast();
}
