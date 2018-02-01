package com.jankrav.learnretrofitusingfragmens.model.client;


import com.jankrav.learnretrofitusingfragmens.presenter.ChoosePresenter;

public interface ChooseGitHubClient {
    void getReposForUser(ChoosePresenter presenter, String userLogin);
}
