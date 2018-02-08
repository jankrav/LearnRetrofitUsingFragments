package com.jankrav.learnretrofitusingfragmens.presenter;

import android.text.TextUtils;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public class DetailFragmentPresenter {
    private GitHubClient client;
    private DetailFragmentView view;

    public DetailFragmentPresenter(DetailFragmentView view, GitHubClient client) {
        this.view = view;
        this.client = client;
    }

    public DetailFragmentView getView() {
        return view;
    }

    public void onSelectedRepo(String repoOwnerLogin, String repoName) {
        if ((repoOwnerLogin != null && !repoOwnerLogin.equals("")) &&
                (repoName != null && !repoName.equals(""))) {
            client.getRepoInfo(repoOwnerLogin, repoName, new GitHubClient.OnDetailDataLoadedListener() {
                @Override
                public void onSuccess(GitHubRepo repo) {
                    DetailFragmentPresenter.this.onGetInfoFromServer(repo);
                }

                @Override
                public void onFailure(Throwable t) {
                    DetailFragmentPresenter.this.view.makeFailureToast();
                }
            });
        } else {
            view.makeUserInfoFailureToast();
        }
    }

    void onGetInfoFromServer(GitHubRepo repo){
        view.showInfo(repoInfoValidation(repo));
        view.makeGoodToast();
    }

    private GitHubRepo repoInfoValidation(GitHubRepo repo) {
        if (TextUtils.isEmpty(repo.getName())) {
            repo.setName("no-name-repository");
        }
        if (TextUtils.isEmpty(repo.getLanguage())) {
            repo.setLanguage("language is not specified ..");
        }
        if (TextUtils.isEmpty(repo.getDescription())) {
            repo.setDescription("description is not specified");
        }
        return repo;
    }

    public void onDetachView() {
        view = null;
    }
}
