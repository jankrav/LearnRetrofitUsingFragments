package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

public class ChooseFragmentPresenter implements ChoosePresenter {
    private ChooseFragmentView view;

    public ChooseFragmentPresenter(ChooseFragmentView view) {
        this.view = view;
    }

}
