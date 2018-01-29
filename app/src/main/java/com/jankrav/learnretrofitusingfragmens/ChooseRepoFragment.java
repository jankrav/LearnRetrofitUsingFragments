package com.jankrav.learnretrofitusingfragmens;

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

import com.jankrav.learnretrofitusingfragmens.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.client.ServiceGenerator;
import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseRepoFragment extends Fragment implements GitHubRepoAdapter.OnChooseItemListener{
    private RecyclerView recyclerView;
    private GitHubClient client;
    private List<GitHubRepo> repos;
    private DetailRepoFragment detail;
    private TextView loginTextView;
    private ImageView avatarImageView;
    private String DEFAULT_USER_LOGIN = "torvalds";
    private static final String USER_LOGIN_KEY = "UserLogin";

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    private String userLogin;

    public ChooseRepoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSelectedRepo(int id) {
        detail = new DetailRepoFragment();

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

        client = ServiceGenerator.getDefaultService();
        loginTextView = view.findViewById(R.id.login);
        avatarImageView = view.findViewById(R.id.avatarPhoto);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        if(userLogin != null && !userLogin.equals(""))
            ;
        else userLogin = DEFAULT_USER_LOGIN;

        Call<List<GitHubRepo>> call = client.reposForUser(userLogin);
        call.enqueue(new Callback<List<GitHubRepo>>() {
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(USER_LOGIN_KEY,userLogin);
    }



}
