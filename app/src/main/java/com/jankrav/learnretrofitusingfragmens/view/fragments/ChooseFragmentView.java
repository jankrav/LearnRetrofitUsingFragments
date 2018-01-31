package com.jankrav.learnretrofitusingfragmens.view.fragments;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

import java.util.List;

public interface ChooseFragmentView {

    void checkoutToDetailFragment(String repoOwnerLogin, String repoName);

    void showInfo(List<GitHubRepo> repos);

    void makeReposIsNullToast();

    void makeFailureToast();

    void makeGoodToast();
}
