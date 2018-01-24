package com.jankrav.learnretrofitusingfragmens.model;

import com.google.gson.annotations.SerializedName;

public class GitHubRepoOwner {
    private String login;
    private Integer id;
    private String url;
    private String type;
    @SerializedName("avatar_url")
    private String avatarUrl;
    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public String toString() {
        return "owner:" + id.toString() + login;
    }
}
