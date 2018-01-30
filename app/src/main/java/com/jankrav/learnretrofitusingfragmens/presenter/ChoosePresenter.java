package com.jankrav.learnretrofitusingfragmens.presenter;


import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

import java.util.List;

public interface ChoosePresenter {
    //view listeners methods
    void onSelectedRepo(int repoListId);
    void onUserChosen(String user);


    //model methods
    void requestResponse(List<GitHubRepo> repos);
    void requestFailure(Throwable t);
}
