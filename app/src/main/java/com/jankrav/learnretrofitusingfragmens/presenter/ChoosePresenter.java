package com.jankrav.learnretrofitusingfragmens.presenter;


import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

public interface ChoosePresenter {
    //presenter events
    void onDetachView();

    //view listeners methods
    void onSelectedRepo(String repoOwnerLogin, String repoName);
    void onUserChosen(String user);

    //for testing
    ChooseFragmentView  getView();
}
