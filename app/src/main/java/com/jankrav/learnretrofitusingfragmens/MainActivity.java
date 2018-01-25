package com.jankrav.learnretrofitusingfragmens;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements GitHubRepoAdapter.OnChooseItemListener {

    private GitHubClient client;
    private RecyclerView recyclerView;
    private List<GitHubRepo> repos;
    private DetailRepoFragment detail = new DetailRepoFragment();
    private ChooseRepoFragment chooser = new ChooseRepoFragment();
    private FragmentTransaction transaction;
    private RecyclerView testRecycler;
    private TextView login;
    private ImageView avatarPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login = findViewById(R.id.login);
        avatarPhoto = findViewById(R.id.avatarPhoto);

        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, chooser);
        transaction.addToBackStack(null);
        transaction.commit();

//        View view;
//        view = findViewById(R.layout.fragment_choose_repo);
//        testRecycler = view.findViewById(R.id.recyclerView);



    }

    @Override
    protected void onStart() {
        super.onStart();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        client = ServiceGenerator.getDefaultService();
        Call<List<GitHubRepo>> call = client.reposForUser("jankrav");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                repos = response.body();

                Picasso.with(MainActivity.this)
                        .load(repos.get(0).getOwner().getAvatarUrl())
                        .into(avatarPhoto);
                login.setText(repos.get(0).getOwner().getLogin());


                recyclerView.setAdapter(new GitHubRepoAdapter(repos, MainActivity.this));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "The network call was a failure",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickRepo(int id) {
        detail.showRepoInfo(
                repos.get(id).getOwner().getLogin(),
                repos.get(id).getName()
        );
        transaction.replace(R.id.fragment_container, detail);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
}
