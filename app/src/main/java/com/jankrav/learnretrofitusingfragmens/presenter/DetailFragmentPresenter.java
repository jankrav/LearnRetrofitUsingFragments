package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;

public class DetailFragmentPresenter implements DetailPresenter{

    private DetailFragmentView view;

    public DetailFragmentPresenter(DetailFragmentView view) {
        this.view = view;
    }
}
