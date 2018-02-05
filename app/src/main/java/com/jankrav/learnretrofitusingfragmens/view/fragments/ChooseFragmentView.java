package com.jankrav.learnretrofitusingfragmens.view.fragments;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.presenter.ChoosePresenter;

import java.util.List;

public interface ChooseFragmentView {
    void setPresenter(ChoosePresenter presenter);

    void checkoutToDetailFragment(String repoOwnerLogin, String repoName);

    void showInfo(List<GitHubRepo> repos);

    void makeReposIsNullToast();

    void makeFailureToast();

    void makeGoodToast();

    void makeUserInfoFailureToast();

    void makeToast(String message);
}
