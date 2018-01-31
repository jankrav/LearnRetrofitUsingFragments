package com.jankrav.learnretrofitusingfragmens.presenter;


import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

import java.util.List;

public interface ChoosePresenter {
    //presenter events
    void onAttachView(ChooseFragmentView view);
    void onDetachView();

    //view listeners methods
    void onSelectedRepo(String repoOwnerLogin, String repoName);
    void onUserChosen(String user);

    //model methods
    void requestResponse(List<GitHubRepo> repos);
    void requestFailure(Throwable t);
}
