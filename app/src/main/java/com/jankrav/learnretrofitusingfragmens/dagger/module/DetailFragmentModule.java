package com.jankrav.learnretrofitusingfragmens.dagger.module;

import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailRepoFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailFragmentModule {
    private DetailRepoFragment currentFragment;
    public DetailFragmentModule(DetailRepoFragment fragment){
        currentFragment = fragment;
    }

    @Provides
    DetailFragmentView provideDetailFragmentView(){
        return currentFragment;
    }

    @Provides
    DetailRepoFragment provideDetailRepoFragment(){
        return currentFragment;
    }
}
