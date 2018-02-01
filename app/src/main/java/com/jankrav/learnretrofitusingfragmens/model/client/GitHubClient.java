package com.jankrav.learnretrofitusingfragmens.model.client;


import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.presenter.ChoosePresenter;
import com.jankrav.learnretrofitusingfragmens.presenter.DetailPresenter;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubClient implements ChooseGitHubClient, DetailGitHubClient {
    private static final GitHubService service = ServiceGenerator.getDefaultService();


    public GitHubClient() {
    }

//   get data to the
    @Override
    public void getReposForUser(final ChoosePresenter presenter, String userLogin) {

        service.reposForUser(userLogin).enqueue(new Callback<List<GitHubRepo>>() {

            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                //response.body return List<GitHubRepo>
                presenter.requestResponse(response.body());
            }
            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                presenter.requestFailure(t);
            }
        });

    }

    @Override
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
