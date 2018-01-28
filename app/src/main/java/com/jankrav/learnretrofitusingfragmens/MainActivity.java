package com.jankrav.learnretrofitusingfragmens;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity implements ChooseRepoFragment.OnChooseItemListener{
    private FragmentTransaction transaction;
    private GitHubClient client;
    private List<GitHubRepo> repos;
    private ChooseRepoFragment chooser = new ChooseRepoFragment();
    private TextView login;
    private ImageView avatarPhoto;
    private DetailRepoFragment detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //default operation's
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init variables
        login = findViewById(R.id.login);
        avatarPhoto = findViewById(R.id.avatarPhoto);

        // show information about user
        getUserInfo("jankrav");

        //show info about user's repositories
        pushChooser();

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


    private void getUserInfo(String user) {
        client = ServiceGenerator.getDefaultService();
        Call<List<GitHubRepo>> call = client.reposForUser(user);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                repos = response.body();
                Picasso.with(MainActivity.this)
                        .load(repos.get(0).getOwner().getAvatarUrl())
                        .into(avatarPhoto);

                login.setText(repos.get(0).getOwner().getLogin());
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "The network failure with connection!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void pushChooser() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, chooser);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onSelectedRepo(int id) {
        detail = new DetailRepoFragment();

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detail);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        detail.showRepoInfo(
                repos.get(id).getOwner().getLogin(),
                repos.get(id).getName()
        );
    }
}
