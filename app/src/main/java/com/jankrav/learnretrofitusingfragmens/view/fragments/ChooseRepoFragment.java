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
    public static String REPO_LIST_ID = "REPO_ID";
    //views
    private RecyclerView recyclerView;
    private TextView loginTextView;
    private ImageView avatarImageView;

    //other
    private ChoosePresenter presenter;
    private DetailRepoFragment detail;



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
    public void checkoutToDetailFragment(int repoListId) {
        Bundle bundle = new Bundle();
        bundle.putInt(REPO_LIST_ID, repoListId);

        detail = new DetailRepoFragment();

        detail.setArguments(bundle);

        FragmentTransaction transaction;
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detail);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showInfo(List<GitHubRepo> repos) {
        loginTextView = getView().findViewById(R.id.login);
        avatarImageView = getView().findViewById(R.id.avatarPhoto);
        recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Picasso.with(getContext()).load(repos.get(0).getOwner().getAvatarUrl())
                .into(avatarImageView);
        loginTextView.setText(repos.get(0).getOwner().getLogin());
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
