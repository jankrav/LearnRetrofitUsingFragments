package com.jankrav.learnretrofitusingfragmens.presenter;

import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class DetailFragmentPresenterTest {
    @Test(expected = NullPointerException.class)
    public void onSelectedRepo_NullPointerException() throws Exception {
        DetailFragmentPresenter presenter = mock(DetailFragmentPresenter.class);
        doThrow(new NullPointerException()).when(presenter).onSelectedRepo(null, null);
        presenter.onSelectedRepo(null, null);
    }

}