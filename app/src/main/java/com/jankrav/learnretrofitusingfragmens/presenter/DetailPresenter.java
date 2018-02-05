package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public interface DetailPresenter {
    //presenter events
    void onAttachView(DetailFragmentView view);
    void onDetachView();

    //view events
    void onSelectedRepo(String repoOwnerLogin, String repoName);

    //for testing
    void setClient(GitHubClient client);
    DetailFragmentView getView();
    void setView(DetailFragmentView view);
}
