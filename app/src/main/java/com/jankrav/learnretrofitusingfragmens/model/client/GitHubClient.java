package com.jankrav.learnretrofitusingfragmens.model.client;


import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.presenter.ChoosePresenter;
import com.jankrav.learnretrofitusingfragmens.presenter.DetailPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubClient {
    private static GitHubClient instance;
    private static final GitHubService service = ServiceGenerator.getDefaultService();
    private List<GitHubRepo> repos;

    private GitHubClient() {

    }


    public static GitHubClient getInstance() {
        if (instance == null) instance = new GitHubClient();
        return instance;
    }

    public void getReposForUser(final ChoosePresenter presenter, String userLogin) {
        service.reposForUser(userLogin).enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                repos = response.body();
                presenter.requestResponse(repos);
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                presenter.requestFailure(t);
            }
        });
    }

    public void getRepoInfo(final DetailPresenter presenter, String repoOwnerLogin, String repoName) {
        service.repoForUser(repoOwnerLogin, repoName).enqueue(new Callback<GitHubRepo>() {
            // if server response than ...
            @Override
            public void onResponse(Call<GitHubRepo> call, Response<GitHubRepo> response) {
                // response.body - return repo info  by owner login and repo name;
                presenter.requestResponse(response.body());
            }

            // if smth goes wrong than ...
            @Override
            public void onFailure(Call<GitHubRepo> call, Throwable t) {
                presenter.requestFailure(t);
            }
        });
    }


}
