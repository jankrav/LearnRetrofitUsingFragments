package com.jankrav.learnretrofitusingfragmens.model.client;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {
    @GET("users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(
            @Path("user") String user
    );

    @GET("repos/{owner}/{repo}")
    Call<GitHubRepo> repoForUser(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
