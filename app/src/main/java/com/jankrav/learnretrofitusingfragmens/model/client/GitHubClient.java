package com.jankrav.learnretrofitusingfragmens.model.client;


import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubClient {
    private static final GitHubService service = ServiceGenerator.getDefaultService();

    public GitHubClient() {
    }

    public static GitHubClient newInstance() {
        GitHubClient client = new GitHubClient();
        return client;
    }
    public void getReposForUser(String userLogin, final OnChooserDataLoadedListener listener) {
            service.reposForUser(userLogin).enqueue(new Callback<List<GitHubRepo>>() {

                @Override
                public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                    listener.onResponse(response.body());
                }

                @Override
                public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                    listener.onFailure(t);
                }
            });
    }

    public void getRepoInfo(String repoOwnerLogin, String repoName, final OnDetailDataLoadedListener listener) {
        service.repoForUser(repoOwnerLogin, repoName).enqueue(new Callback<GitHubRepo>() {
            // if server response than ...
            @Override
            public void onResponse(Call<GitHubRepo> call, Response<GitHubRepo> response) {
                // response.body - return repo info  by owner login and repo name;
                listener.onResponse(response.body());
            }

            // if smth goes wrong than ...
            @Override
            public void onFailure(Call<GitHubRepo> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public interface OnChooserDataLoadedListener {
        void onResponse(List<GitHubRepo> repos);

        void onFailure(Throwable t);
    }

    public interface OnDetailDataLoadedListener {
        void onResponse(GitHubRepo repo);

        void onFailure(Throwable t);
    }
}
