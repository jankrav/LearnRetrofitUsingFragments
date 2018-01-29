package com.jankrav.learnretrofitusingfragmens.view;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;
import com.jankrav.learnretrofitusingfragmens.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //default operation's
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ChooseRepoFragment chooser = new ChooseRepoFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, chooser);
        transaction.commit();
    }
}
