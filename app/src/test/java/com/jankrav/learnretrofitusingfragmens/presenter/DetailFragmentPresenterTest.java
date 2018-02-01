package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailRepoFragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class DetailFragmentPresenterTest {
    @Mock
    DetailFragmentView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        view = new DetailRepoFragment();
    }

    @Test
    public void testSomeFeature() {

    }

    @Test(expected = NullPointerException.class)
    public void onSelectedRepo_NullPointerException() throws Exception {
        DetailFragmentPresenter presenter = mock(DetailFragmentPresenter.class);
        doThrow(new NullPointerException()).when(presenter).onSelectedRepo(null, null);
        presenter.onSelectedRepo(null, null);
    }
}

