package com.jankrav.learnretrofitusingfragmens.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jankrav.learnretrofitusingfragmens.R;
import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.presenter.DetailPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailRepoFragment extends Fragment implements DetailFragmentView {

    private TextView name, language, description, watchers, defaultBranch;

    private DetailPresenter presenter;

    private String repoOwnerLogin;
    private String repoName;

    public DetailRepoFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static DetailRepoFragment newInstance(DetailPresenter presenter) {
        DetailRepoFragment f = new DetailRepoFragment();
        f.setPresenter(presenter);
        return f;
    }

    @Override
    public void setPresenter(DetailPresenter presenter) {
        this.presenter = presenter;
        presenter.onAttachView(this);
    }

    private void initFields(View view) {
        if (view != null) {
            name = view.findViewById(R.id.name);
            language = view.findViewById(R.id.language);
            description = view.findViewById(R.id.description);
            watchers = view.findViewById(R.id.watchers);
            defaultBranch = view.findViewById(R.id.defaultBranch);
        } else {
            Toast.makeText(getContext(), "Error. Can't find resource. Restart app!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_repo, container, false);
        initFields(view);
        if (savedInstanceState == null) {
            repoOwnerLogin = getArguments().getString(ChooseRepoFragment.REPO_OWNER_LOGIN);
            repoName = getArguments().getString(ChooseRepoFragment.REPO_NAME);
        } else {
            repoOwnerLogin = savedInstanceState.getString(ChooseRepoFragment.REPO_OWNER_LOGIN);
            repoName = savedInstanceState.getString(ChooseRepoFragment.REPO_NAME);
        }
        return view;
    }

    @Override
    public void showInfo(GitHubRepo repo) {
        name.setText(repo.getName());
        language.setText(repo.getLanguage());
        description.setText(repo.getDescription());
        watchers.setText(repo.getWatchers().toString());
        defaultBranch.setText(repo.getDefaultBranch());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter.onAttachView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onSelectedRepo(repoOwnerLogin, repoName);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ChooseRepoFragment.REPO_OWNER_LOGIN, repoOwnerLogin);
        outState.putString(ChooseRepoFragment.REPO_NAME, repoName);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDetachView();
    }

    // toast's
    @Override
    public void makeFailureToast() {
        Toast.makeText(getContext(), "The network is failure!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeGoodToast() {
        Toast.makeText(getContext(), "Everything is okey! ;)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeRepoIsNullToast() {
        Toast.makeText(getContext(), "Repo is null!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeUserInfoFailureToast() {
        Toast.makeText(getContext(), "User login or repository name is empty.", Toast.LENGTH_SHORT).show();
    }
}
