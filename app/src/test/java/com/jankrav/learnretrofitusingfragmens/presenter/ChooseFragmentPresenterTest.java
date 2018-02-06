package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChooseFragmentPresenterTest {
    @Mock
    ChooseRepoFragment view;

    @Mock
    GitHubClient client;

    ChooseFragmentPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ChooseFragmentPresenter(client);
        view.setPresenter(presenter);
    }

    @Test
    public void setPresenter_checkDoesViewIsNotNull(){
        ChooseFragmentPresenter presenter = new ChooseFragmentPresenter(client);
        ChooseRepoFragment view = ChooseRepoFragment.newInstance(presenter);
        assertNotNull(presenter.getView());
    }

    @Test
    public void onChosenUser_getReposForUserCalled() {
        presenter.onUserChosen("user");
    }

    @Test
    public void onSelectedRepo_callCheckOutToOtherFragment() {
        // this must work without setView
        presenter.setView(view);
        presenter.onSelectedRepo("user", "repo");
        verify(view).checkoutToDetailFragment("user", "repo");
    }

    @Test
    public void onAttachView_called() throws Exception {
        ChooseRepoFragment view = new ChooseRepoFragment();
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);
        view.setPresenter(presenter);
        verify(presenter).onAttachView(view);
    }

    @Test
    public void onDetach_called() {
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);
        ChooseRepoFragment view = new ChooseRepoFragment();
        view.setPresenter(presenter);
        view.onDetach();
        verify(presenter).onDetachView();
    }

    @Test
    public void onUserChosen_userLoginIsEmpty() {
        presenter.setView(view);
        presenter.onUserChosen(null);
        verify(view).makeUserLoginIsNullToast();
    }

    @Test
    public void onUserChosen_calledGetReposForUser(){
        presenter.setView(view);
        presenter.onUserChosen("User");
        verify(client).getReposForUser("User", presenter);
    }

    @Test
    public void setPresenter_onAttachViewRespond() {
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);
        ChooseRepoFragment view = new ChooseRepoFragment();
        view.setPresenter(presenter);
        verify(presenter).onAttachView(view);
    }

    @Test
    public void onResponse_called(){
        presenter.setView(view);
        presenter.onResponse(new ArrayList<GitHubRepo>());
        verify(view).showInfo(new ArrayList<GitHubRepo>());
    }

    @After
    public void tearDown() {
        presenter = null;
    }
}

