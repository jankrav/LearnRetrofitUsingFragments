package com.jankrav.learnretrofitusingfragmens.presenter;

import android.content.Context;

import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChooseFragmentPresenterTest {
    @Mock
    ChooseRepoFragment view;

    @Mock
    Context context;

    @Mock
    GitHubClient client;


    ChooseFragmentPresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new ChooseFragmentPresenter();
        presenter.setClient(client);
        view.setPresenter(presenter);
    }



    @Test
    public void onChosenUser_getReposForUserCalled(){
        presenter.onUserChosen("user");
        verify(client).getReposForUser("user", presenter);
    }

    @Test
    public void onSelectedRepo_callCheckOutToOtherFragment(){
        // this must work without setView
        presenter.setView(view);
        presenter.onSelectedRepo("user", "repo");
        verify(view).checkoutToDetailFragment("user", "repo");
    }

    @Test
    public void onAttachView_called() throws Exception{
        ChooseRepoFragment view = new ChooseRepoFragment();
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);
        view.setPresenter(presenter);
        verify(presenter).onAttachView(view);
    }

    @Test
    public void onDetach_called(){
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);
        ChooseRepoFragment view = new ChooseRepoFragment();
        view.setPresenter(presenter);
        view.onDetach();
        verify(presenter).onDetachView();
    }

    @Test
    public void onError_called(){
//        assertNotNull(view);
//        setView must not invoke independently
        assertNull(presenter.getView());
        presenter.setView(view);
        presenter.onError();
        verify(view).makeUserLoginIsNullToast();
    }

    @Test public void setPresenter_onAttachViewRespond(){
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);
        ChooseRepoFragment view = new ChooseRepoFragment();
        view.setPresenter(presenter);
        verify(presenter).onAttachView(view);
    }

    @After
    public void tearDown(){
        presenter = null;
    }
}

