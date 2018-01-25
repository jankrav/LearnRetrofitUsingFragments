package com.jankrav.learnretrofitusingfragmens;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jankrav.learnretrofitusingfragmens.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.client.ServiceGenerator;
import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailRepoFragment extends Fragment {
    private TextView name, language, description, watchers, defaultBranch;
    private View fragmentView;


    private GitHubClient client;

    public DetailRepoFragment() {
        // Required empty public constructor
    }


    //must find repo by properties
    public void showRepoInfo(String owner, String repo) {

        client = ServiceGenerator.getDefaultService();
        Call<GitHubRepo> call = client.repoForUser(owner, repo);
        call.enqueue(new Callback<GitHubRepo>() {
            @Override
            public void onResponse(Call<GitHubRepo> call, Response<GitHubRepo> response) {
                GitHubRepo repo = response.body();
                name.setText(repo.getName());
                language.setText(repo.getDescription());
                description.setText(repo.getDescription());
                watchers.setText(repo.getWatchers().toString());
                defaultBranch.setText(repo.getDefaultBranch());
            }

            @Override
            public void onFailure(Call<GitHubRepo> call, Throwable t) {
                Toast.makeText(fragmentView.getContext(), "The network call was a failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_detail_repo, container, false);

        name = fragmentView.findViewById(R.id.name);
        language = fragmentView.findViewById(R.id.language);
        description = fragmentView.findViewById(R.id.description);
        watchers = fragmentView.findViewById(R.id.watchers);
        defaultBranch = fragmentView.findViewById(R.id.defaultBranch);

        return fragmentView;
    }


}
