package com.jankrav.learnretrofitusingfragmens.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jankrav.learnretrofitusingfragmens.R;
import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.presenter.ChoosePresenter;

import java.util.List;


public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.Holder> {
    private List<GitHubRepo> repos;

    private ChoosePresenter presenter;

    public GitHubRepoAdapter(List<GitHubRepo> repos, ChoosePresenter presenter) {
        this.repos = repos;
        this.presenter = presenter;
    }

    protected class Holder extends RecyclerView.ViewHolder {
        private TextView repoName;

        public Holder(View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.list_item_repo_name);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new Holder(inflater.inflate(R.layout.list_item_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.repoName.setText(repos.get(position).getName());
        holder.repoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null)
                    presenter.onSelectedRepo(
                            repos.get(position).getOwner().getLogin(),
                            repos.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }
}
