package com.jankrav.learnretrofitusingfragmens.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jankrav.learnretrofitusingfragmens.R;
import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.presenter.ChooseFragmentPresenter;
import com.jankrav.learnretrofitusingfragmens.presenter.ChoosePresenter;
import com.jankrav.learnretrofitusingfragmens.view.adapters.GitHubRepoAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseRepoFragment extends Fragment implements ChooseFragmentView {
    public static final String REPO_NAME = "REPO_NAME";
    public static final String REPO_OWNER_LOGIN = "REPO_OWNER_LOGIN";

    private ChoosePresenter presenter;


    private TextView loginTextView;
    private ImageView avatarImageView;
    private RecyclerView recyclerView;

    public ChooseRepoFragment() {
        // Required empty public constructor
    }

    private void initFields(View view) {
        if(view != null) {
            loginTextView = view.findViewById(R.id.loginTextView);
            avatarImageView = view.findViewById(R.id.avatarImageView);
            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            Toast.makeText(getContext(), "Can't find resource's. Please restart app!", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    public static ChooseRepoFragment newInstance() {
        ChooseRepoFragment f = new ChooseRepoFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_repo, container, false);
        initFields(view);
        presenter = new ChooseFragmentPresenter(this, GitHubClient.newInstance());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onUserChosen("jankrav");
    }


    @Override
    public void onDestroyView() {
        presenter.onDetachView();
        super.onDestroyView();
    }

    @Override
    public void checkoutToDetailFragment(String repoOwnerLogin, String repoName) {
        Bundle bundle = new Bundle();
        bundle.putString(REPO_OWNER_LOGIN, repoOwnerLogin);
        bundle.putString(REPO_NAME, repoName);

        DetailRepoFragment detail =
                DetailRepoFragment.newInstance();

        detail.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detail)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showInfo(List<GitHubRepo> repos) {
        loginTextView.setText(repos.get(0).getOwner().getLogin());
        Picasso.with(getContext()).load(repos.get(0).getOwner().getAvatarUrl())
                .into(avatarImageView);
        recyclerView.setAdapter(new GitHubRepoAdapter(repos, presenter));
    }

    //    toast's
    @Override
    public void makeGoodToast() {
        Toast.makeText(getContext(), "Network is response ;)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeReposIsNullToast() {
        Toast.makeText(getContext(), "Server return empty list of repositories for this user", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeFailureToast() {
        Toast.makeText(getContext(), "The network is failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeUserInfoFailureToast() {
        Toast.makeText(getContext(), "User login or repo name is empty!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeUserLoginIsNullToast() {
        Toast.makeText(getContext(), "User login is empty", Toast.LENGTH_SHORT).show();
    }

}
