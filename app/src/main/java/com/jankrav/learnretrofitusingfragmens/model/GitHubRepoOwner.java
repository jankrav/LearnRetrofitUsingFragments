package com.jankrav.learnretrofitusingfragmens.model;

import com.google.gson.annotations.SerializedName;

public class GitHubRepoOwner {
    @SerializedName("id")
    private Integer id;

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public String toString() {
        return "owner:" + id.toString() + login;
    }
}
