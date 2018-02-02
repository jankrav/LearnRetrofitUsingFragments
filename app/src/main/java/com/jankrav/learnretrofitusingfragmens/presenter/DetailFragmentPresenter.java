package com.jankrav.learnretrofitusingfragmens.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.DetailGitHubClient;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public class DetailFragmentPresenter implements DetailPresenter {
    private DetailGitHubClient client = new GitHubClient();
    private DetailFragmentView view;

    public DetailFragmentPresenter() {
    }

//    for testing
    @Override
    public void setClient(DetailGitHubClient client) {
        this.client = client;
    }

    @Override
    public void onAttachView(DetailFragmentView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        view = null;
    }

    @Override
    public void onSelectedRepo(String repoOwnerLogin, String repoName) {
        client.getRepoInfo(this, repoOwnerLogin, repoName);
    }

    @Override
    public void requestResponse(GitHubRepo repo) {
        if (repo != null ){
            view.showInfo(validateRepoInfo(repo));
            view.makeGoodToast();
        }
        else view.makeRepoIsNullToast();
    }

    private GitHubRepo validateRepoInfo(GitHubRepo repo){
        if (TextUtils.isEmpty(repo.getName())) repo.setName("no-name-repository");
        if (TextUtils.isEmpty(repo.getLanguage())) repo.setLanguage("language is not specified ..");
        if (TextUtils.isEmpty(repo.getDescription())) repo.setDescription("description is not specified");

        return repo;
    }

    @Override
    public void requestFailure(Throwable t) {
        view.makeFailureToast();
    }
}
