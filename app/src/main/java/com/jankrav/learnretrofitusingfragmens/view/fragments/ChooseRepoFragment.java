package com.jankrav.learnretrofitusingfragmens.view.fragments;

import android.os.Bundle;
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

    public ChooseRepoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new ChooseFragmentPresenter(this);
        presenter.onUserChosen("jankrav");
        return inflater.inflate(R.layout.fragment_choose_repo, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter = null;
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
        //user login
        TextView loginTextView = getView().findViewById(R.id.login);
        loginTextView.setText(repos.get(0).getOwner().getLogin());
        // show user avatar
        ImageView avatarImageView = getView().findViewById(R.id.avatarPhoto);
        Picasso.with(getContext()).load(repos.get(0).getOwner().getAvatarUrl())
                .into(avatarImageView);

        // list of the repos
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new GitHubRepoAdapter(repos, presenter));
    }

    @Override
    public void makeReposIsNullToast() {
        Toast.makeText(getContext(), "Server return that repos is null", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeFailureToast() {
        Toast.makeText(getContext(), "The network is failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeGoodToast() {
        Toast.makeText(getContext(), "Network is response ;)", Toast.LENGTH_SHORT).show();
    }

}
