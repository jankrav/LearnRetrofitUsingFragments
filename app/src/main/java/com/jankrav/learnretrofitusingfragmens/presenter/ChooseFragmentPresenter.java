package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

import java.util.List;

public class ChooseFragmentPresenter {
    private GitHubClient client;
    private ChooseFragmentView view;

    public ChooseFragmentPresenter(ChooseFragmentView view, GitHubClient client) {
        this.view = view;
        this.client = client;
    }

    public ChooseFragmentView getView() {
        return view;
    }

    public void onDetachView() {
        view = null;
    }

    public void onSearchUser(String user) {
        if (user != null && !user.equals("")) {
            client.getReposForUser(user, new GitHubClient.OnChooserDataLoadedListener() {
                @Override
                public void onSuccess(List<GitHubRepo> repos) {
                    if (repos!=null){
                        view.showInfo(repos);
                        view.makeGoodToast();
                    } else {
                        view.makeReposIsNullToast();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    view.makeFailureToast();
                }
            });
        } else {
            view.makeUserLoginIsNullToast();
        }
    }



    public void onSelectedRepo(String repoOwnerLogin, String repoName) {
        if ((repoOwnerLogin != null && !repoOwnerLogin.equals("")) &&
                (repoName != null && !repoName.equals(""))) {
            view.checkoutToDetailFragment(repoOwnerLogin, repoName);
        } else {
            view.makeUserInfoFailureToast();
        }
    }
}
