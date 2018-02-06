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
import static org.junit.Assert.assertNull;
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
        presenter = new ChooseFragmentPresenter(view, client);
    }

    @Test
    public void setPresenter_checkDoesViewIsNotNull(){
        ChooseRepoFragment view = ChooseRepoFragment.newInstance();
        ChooseFragmentPresenter presenter = new ChooseFragmentPresenter(view, client);
        assertNotNull(presenter.getView());
    }

    @Test
    public void onChosenUser_getReposForUserCalled() {
        presenter.onUserChosen("user");
    }

    @Test
    public void onSelectedRepo_callCheckOutToOtherFragment() {
        presenter.onSelectedRepo("user", "repo");
        verify(view).checkoutToDetailFragment("user", "repo");
    }

    @Test
    public void onDetachView_called() {
        presenter.onDetachView();
        assertNull(presenter.getView());
    }

    @Test
    public void onUserChosen_userLoginIsEmpty() {
        presenter.onUserChosen(null);
        verify(view).makeUserLoginIsNullToast();
    }

    @Test
    public void onUserChosen_calledGetReposForUser(){
        presenter.onUserChosen("User");
        verify(client).getReposForUser("User", presenter);
    }

    @Test
    public void onResponse_called(){
        presenter.onResponse(new ArrayList<GitHubRepo>());
        verify(view).showInfo(new ArrayList<GitHubRepo>());
    }

    @After
    public void tearDown() {
        presenter = null;
    }
}

