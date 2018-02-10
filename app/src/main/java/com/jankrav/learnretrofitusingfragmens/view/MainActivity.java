package com.jankrav.learnretrofitusingfragmens.view;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jankrav.learnretrofitusingfragmens.R;
import com.jankrav.learnretrofitusingfragmens.dagger.App;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //default operation's
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        App.getComponent().inject(this);

        if(savedInstanceState == null){
            // if activity lauch first time then create chooser
            ChooseRepoFragment chooser = ChooseRepoFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, chooser);
            transaction.commit();
        }

    }
}
