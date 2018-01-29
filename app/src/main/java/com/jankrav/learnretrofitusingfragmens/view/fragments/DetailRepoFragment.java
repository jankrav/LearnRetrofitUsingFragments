package com.jankrav.learnretrofitusingfragmens.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jankrav.learnretrofitusingfragmens.R;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.model.client.ServiceGenerator;
import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailRepoFragment extends Fragment {
    private static String REPO_KEY = "2502";
    private static String DESCRIPTION_KEY = "1998";
    private static String LANGUAGE_KEY = "1301";
    private static String BRANCH_KEY = "1997";
    private static String WATCHERS_KEY = "1972";

    private String owner, repo;
    private TextView name, language, description, watchers, defaultBranch;


    private View fragmentView;
    private GitHubClient client = ServiceGenerator.getDefaultService();
    ;
    private Context context;

    public DetailRepoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_detail_repo, container, false);

        initFields();

        if (savedInstanceState != null) {
            name.setText(savedInstanceState.getString(REPO_KEY));
            language.setText(savedInstanceState.getString(LANGUAGE_KEY));
            description.setText(savedInstanceState.getString(DESCRIPTION_KEY));
            watchers.setText(savedInstanceState.getString(WATCHERS_KEY));
            defaultBranch.setText(savedInstanceState.getString(BRANCH_KEY));
        }

        return fragmentView;
    }

    private void initFields() {
        if (fragmentView != null) {
            name = fragmentView.findViewById(R.id.name);
            language = fragmentView.findViewById(R.id.language);
            description = fragmentView.findViewById(R.id.description);
            watchers = fragmentView.findViewById(R.id.watchers);
            defaultBranch = fragmentView.findViewById(R.id.defaultBranch);
        }
    }

    //must find repo by properties
    public void showRepoInfo(@NonNull String owner, @NonNull String repo) {
        Call<GitHubRepo> call = client.repoForUser(owner, repo);

        call.enqueue(new Callback<GitHubRepo>() {
            // if server response than ...
            @Override
            public void onResponse(Call<GitHubRepo> call, Response<GitHubRepo> response) {
                GitHubRepo repo = response.body();
                if (!TextUtils.isEmpty(repo.getName()))
                    name.setText(repo.getName());
                if (!TextUtils.isEmpty(repo.getLanguage()))
                    language.setText(repo.getLanguage());
                if (!TextUtils.isEmpty(repo.getDescription()))
                    description.setText(repo.getDescription());
                if (!TextUtils.isEmpty(repo.getWatchers().toString()))
                    watchers.setText(repo.getWatchers().toString());
                if (!TextUtils.isEmpty(repo.getDefaultBranch()))
                    defaultBranch.setText(repo.getDefaultBranch());
            }

            // if smth goes wrong than ...
            @Override
            public void onFailure(Call<GitHubRepo> call, Throwable t) {
                Toast.makeText(context, "The network call was a failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(REPO_KEY, name.getText().toString());
        outState.putString(LANGUAGE_KEY, language.getText().toString());
        outState.putString(DESCRIPTION_KEY, description.getText().toString());
        outState.putString(WATCHERS_KEY, watchers.getText().toString());
        outState.putString(BRANCH_KEY, defaultBranch.getText().toString());
    }


}
