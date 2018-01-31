package com.jankrav.learnretrofitusingfragmens.presenter;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ChooseFragmentPresenterTest {
    @Test(expected = NullPointerException.class)
    public void onUserChosen() throws Exception {
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);
        doThrow(new NullPointerException()).when(presenter).onUserChosen(null);
        presenter.onUserChosen(null);
    }

    @Test
    public void onSelectedRepo() throws Exception {
    }

}