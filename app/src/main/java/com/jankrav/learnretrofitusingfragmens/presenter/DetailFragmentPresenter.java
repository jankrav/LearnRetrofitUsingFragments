package com.jankrav.learnretrofitusingfragmens.presenter;

import android.text.TextUtils;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public class DetailFragmentPresenter implements DetailPresenter, GitHubClient.OnDetailDataLoadedListener {
    private GitHubClient client;
    private DetailFragmentView view;

    public DetailFragmentPresenter(GitHubClient client) {
        this.client = client;
    }

    @Override
    public void onSelectedRepo(String repoOwnerLogin, String repoName){
        if((repoOwnerLogin != null && !repoOwnerLogin.equals("")) &&
                (repoName != null && !repoName.equals("")))
            client.getRepoInfo(repoOwnerLogin, repoName, this);
        else view.makeUserInfoFailureToast();
    }

    @Override
    public void onResponse(GitHubRepo repo) {
        if (repo != null) {
            view.showInfo(repoInfoValidation(repo));
            view.makeGoodToast();
        } else view.makeRepoIsNullToast();
    }

    @Override
    public void onFailure(Throwable t) {
        view.makeFailureToast();
    }

    private GitHubRepo repoInfoValidation(GitHubRepo repo) {
        if (TextUtils.isEmpty(repo.getName())) repo.setName("no-name-repository");
        if (TextUtils.isEmpty(repo.getLanguage())) repo.setLanguage("language is not specified ..");
        if (TextUtils.isEmpty(repo.getDescription()))
            repo.setDescription("description is not specified");

        return repo;
    }


    @Override
    public void onAttachView(DetailFragmentView view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        view = null;
    }

//    for testing
    @Override public DetailFragmentView getView() {
        return view;
    }

    @Override public void setView(DetailFragmentView view) {
        this.view = view;
    }
}
