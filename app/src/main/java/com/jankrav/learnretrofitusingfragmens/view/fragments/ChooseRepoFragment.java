package com.jankrav.learnretrofitusingfragmens.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

    @NonNull
    public static ChooseRepoFragment newInstance(){
        return new ChooseRepoFragment();
    }

    // method for testing
    @Override
    public void setPresenter(ChoosePresenter presenter) {
        this.presenter = presenter;
        presenter.onAttachView(this);
    }



    private void initFields(View view) {
        loginTextView = view.findViewById(R.id.loginTextView);
        avatarImageView = view.findViewById(R.id.avatarImageView);

        recyclerView = view.findViewById(R.id.recyclerView);
//        set up layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_repo, container, false);
        initFields(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onUserChosen("jankrav");
    }

    @Override
    public void checkoutToDetailFragment(String repoOwnerLogin, String repoName) {
        Bundle bundle = new Bundle();

        bundle.putString(REPO_OWNER_LOGIN, repoOwnerLogin);
        bundle.putString(REPO_NAME, repoName);

        DetailRepoFragment detail = new DetailRepoFragment();

        detail.setArguments(bundle);

        FragmentTransaction transaction;
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detail);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showInfo(List<GitHubRepo> repos) {
        loginTextView.setText(repos.get(0).getOwner().getLogin());
        Picasso.with(getContext()).load(repos.get(0).getOwner().getAvatarUrl())
                .into(avatarImageView);

        // list of the repos
        recyclerView.setAdapter(new GitHubRepoAdapter(repos, presenter));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter.onAttachView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDetachView();
    }

    // toast's
    @Override
    public void makeReposIsNullToast() {
        Toast.makeText(getContext(), "Server return empty list of repositories for this user", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeFailureToast() {
        Toast.makeText(getContext(), "The network is failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeGoodToast() {
        Toast.makeText(getContext(), "Network is response ;)", Toast.LENGTH_SHORT).show();
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
