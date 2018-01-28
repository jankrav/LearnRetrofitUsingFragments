package com.jankrav.learnretrofitusingfragmens;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jankrav.learnretrofitusingfragmens.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.client.ServiceGenerator;
import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseRepoFragment extends Fragment {
    private RecyclerView recyclerView;
    private GitHubClient client;
    private List<GitHubRepo> repos;
    private DetailRepoFragment detail;
    private Context context;

    // handle user's click's
    interface OnChooseItemListener {
        void onSelectedRepo(int id);
    }

    private OnChooseItemListener listener;

    public ChooseRepoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        listener = (OnChooseItemListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_choose_repo, container, false);

        client = ServiceGenerator.getDefaultService();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        getUserRepos("jankrav");


        return view;
    }

    private void getUserRepos(String user) {
        Call<List<GitHubRepo>> call = client.reposForUser(user);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                repos = response.body();
                recyclerView.setAdapter(new GitHubRepoAdapter(repos, listener));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(context,
                        "The network call was a failure",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
