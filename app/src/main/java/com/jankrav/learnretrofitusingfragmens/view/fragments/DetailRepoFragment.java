package com.jankrav.learnretrofitusingfragmens.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jankrav.learnretrofitusingfragmens.R;
import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.presenter.DetailFragmentPresenter;
import com.jankrav.learnretrofitusingfragmens.presenter.DetailPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailRepoFragment extends Fragment implements DetailFragmentView {

    private static String REPO_KEY = "2502";
    private static String DESCRIPTION_KEY = "1998";
    private static String LANGUAGE_KEY = "1301";
    private static String BRANCH_KEY = "1997";
    private static String WATCHERS_KEY = "1972";

    private TextView name, language, description, watchers, defaultBranch;

    private DetailPresenter presenter;
    private View fragmentView;

    public DetailRepoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new DetailFragmentPresenter();
        presenter.onAttachView(this);
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
        } else {
            Bundle bundle = getArguments();
            String repoOwnerLogin = bundle.getString(ChooseRepoFragment.REPO_OWNER_LOGIN);
            String repoName = bundle.getString(ChooseRepoFragment.REPO_NAME);
            presenter.onSelectedRepo(repoOwnerLogin, repoName);
        }

        return fragmentView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDetachView();
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(REPO_KEY, name.getText().toString());
        outState.putString(LANGUAGE_KEY, language.getText().toString());
        outState.putString(DESCRIPTION_KEY, description.getText().toString());
        outState.putString(WATCHERS_KEY, watchers.getText().toString());
        outState.putString(BRANCH_KEY, defaultBranch.getText().toString());
    }

    // DetailFragmentView methods
    @Override
    public void showInfo(GitHubRepo repo) {
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

    @Override
    public void makeRepoIsNullToast() {
        Toast.makeText(getContext(), "Repo is null!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeFailureToast() {
        Toast.makeText(getContext(), "The network is failure!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeGoodToast() {
        Toast.makeText(getContext(), "Everything is okey! ;)", Toast.LENGTH_SHORT).show();
    }
}
