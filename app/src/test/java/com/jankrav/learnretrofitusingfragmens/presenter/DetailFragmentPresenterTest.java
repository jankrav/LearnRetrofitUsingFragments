package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailFragmentView;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailRepoFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DetailFragmentPresenterTest {
    @Mock
    DetailPresenter presenter;
    @Mock
    DetailGitHubClient client;

    DetailFragmentView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
//        view = new DetailRepoFragment();
        view = new DetailRepoFragment();
        view.setPresenter(presenter);
        presenter.setClient(client);
    }

    @After
    public void tearDown(){
        view = null;
    }

    @Test(expected = NullPointerException.class)
    public void onSelectedRepo_NullPointerException() throws Exception {
        DetailFragmentPresenter presenter = mock(DetailFragmentPresenter.class);
        doThrow(new NullPointerException()).when(presenter).onSelectedRepo(null, null);
        presenter.onSelectedRepo(null, null);
    }

}

