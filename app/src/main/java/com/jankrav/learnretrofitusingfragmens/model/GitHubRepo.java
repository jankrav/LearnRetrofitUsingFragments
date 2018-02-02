package com.jankrav.learnretrofitusingfragmens.model;

import com.google.gson.annotations.SerializedName;

public class GitHubRepo {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("watchers")
    private Integer watchers;
    @SerializedName("language")
    private String language;
    @SerializedName("default_branch")
    private String defaultBranch;
    @SerializedName("owner")
    private GitHubRepoOwner owner;

    public GitHubRepo() {
    }

    public String getLanguage() {
        return language;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public GitHubRepoOwner getOwner() {
        return owner;
    }

//     Setter's need for validate info about User
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    @Override
    public String toString() {
        return "repo: " + id.toString() + " " + name + " " + owner.toString();
    }
}
