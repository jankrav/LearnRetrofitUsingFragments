package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.DetailGitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public interface DetailPresenter {
    //presenter events
    void onAttachView(DetailFragmentView view);
    void onDetachView();

    //view events
    void onSelectedRepo(String repoOwnerLogin, String repoName);

    //model callback methods
    void requestResponse(GitHubRepo repo);
    void requestFailure(Throwable t);

    void setClient(DetailGitHubClient client);

    DetailFragmentView getView();
}
