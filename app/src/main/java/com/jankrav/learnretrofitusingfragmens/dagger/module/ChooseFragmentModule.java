package com.jankrav.learnretrofitusingfragmens.dagger.module;

import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ChooseFragmentModule {
    private ChooseRepoFragment currentFragment;
    public ChooseFragmentModule(ChooseRepoFragment fragment){
        currentFragment = fragment;
    }

    @Provides
    ChooseFragmentView provideChooseFragmentView(){
        return currentFragment;
    }

    @Provides
    ChooseRepoFragment provideChooseRepoFragment(){
        return currentFragment;
    }
}
