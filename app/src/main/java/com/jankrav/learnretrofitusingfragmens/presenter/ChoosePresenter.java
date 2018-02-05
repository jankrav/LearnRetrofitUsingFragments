package com.jankrav.learnretrofitusingfragmens.presenter;


import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

public interface ChoosePresenter {
    //presenter events
    void onAttachView(ChooseFragmentView view);
    void onDetachView();

    //view listeners methods
    void onSelectedRepo(String repoOwnerLogin, String repoName);
    void onUserChosen(String user);

    //for testing
    void setClient(GitHubClient client);
    void setView(ChooseFragmentView view);
    ChooseFragmentView  getView();
}
