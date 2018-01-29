package com.jankrav.learnretrofitusingfragmens;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    // login of user about who we want watch information
    private String userLogin = "jankrav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //default operation's
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ChooseRepoFragment chooser = new ChooseRepoFragment();
        chooser.setUserLogin(userLogin);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, chooser);
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
