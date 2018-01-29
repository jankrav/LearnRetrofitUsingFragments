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
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.model.client.ServiceGenerator;
import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.view.adapters.GitHubRepoAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseRepoFragment extends Fragment implements GitHubRepoAdapter.OnChooseItemListener {
    private String userLogin;
    private RecyclerView recyclerView;
    private List<GitHubRepo> repos;
    private TextView loginTextView;
    private ImageView avatarImageView;


    private static final String USER_LOGIN_KEY = "UserLogin";

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public ChooseRepoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSelectedRepo(int id) {
        DetailRepoFragment detail = new DetailRepoFragment();

        FragmentTransaction transaction;

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detail);
        transaction.addToBackStack(null);

        transaction.commit();

        detail.showRepoInfo(
                repos.get(id).getOwner().getLogin(),
                repos.get(id).getName()
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_choose_repo, container, false);

        //init fields

        //Retrofit client
        GitHubClient client = ServiceGenerator.getDefaultService();
        loginTextView = view.findViewById(R.id.login);
        avatarImageView = view.findViewById(R.id.avatarPhoto);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        //Get repos from the server for specific user by login asynchronously
        client.reposForUser("jankrav").enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                repos = response.body();
                Picasso.with(getContext()).load(repos.get(0).getOwner().getAvatarUrl())
                        .into(avatarImageView);
                loginTextView.setText(repos.get(0).getOwner().getLogin());
                recyclerView.setAdapter(new GitHubRepoAdapter(repos, ChooseRepoFragment.this));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(getContext(),
                        "The network call was a failure",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



}
