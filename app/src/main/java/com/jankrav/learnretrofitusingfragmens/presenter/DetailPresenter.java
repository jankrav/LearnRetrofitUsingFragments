package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

public interface DetailPresenter {
    void onSelectedRepo(int id);

    //model methods
    void requestResponse(GitHubRepo repo);
    void requestFailure(Throwable t);
}
