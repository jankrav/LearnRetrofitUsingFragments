package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public interface DetailPresenter {
    //presenter events
    void onDetachView();

    //view events
    void onSelectedRepo(String repoOwnerLogin, String repoName);

    //for testing
    DetailFragmentView getView();
}
