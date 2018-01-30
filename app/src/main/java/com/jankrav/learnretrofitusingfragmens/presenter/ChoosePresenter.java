package com.jankrav.learnretrofitusingfragmens.presenter;


import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

import java.util.List;

public interface ChoosePresenter {
    void onSelectedRepo(int id);
    void onUserChosen(String user);

    void requestResponse(List<GitHubRepo> repos);
    void requestFailure(Throwable t);
}
