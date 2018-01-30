package com.jankrav.learnretrofitusingfragmens.view.fragments;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

public interface DetailFragmentView {
    void showInfo(GitHubRepo repo);

    void makeReposIsNullToast();

    void makeFailureToast();

    void makeGoodToast();
}
