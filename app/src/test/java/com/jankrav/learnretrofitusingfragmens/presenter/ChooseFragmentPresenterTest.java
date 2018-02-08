package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChooseFragmentPresenterTest {
    @Mock
    ChooseRepoFragment view;

    @Mock
    GitHubClient client;

    @Mock
    GitHubClient.OnChooserDataLoadedListener listener;

    @Captor
    ArgumentCaptor<List<GitHubRepo>> captor;

    ChooseFragmentPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ChooseFragmentPresenter(view, client);
    }



    @Test
    public void onUserChosen_getResponseFromServer() throws Throwable {
        Answer answer = new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final GitHubClient.OnChooserDataLoadedListener listener = invocation.getArgument(1);
                listener.onSuccess(new ArrayList<GitHubRepo>());
                verify(listener, times(1)).onSuccess(captor.capture());
                presenter.onUserChosen(captor.getValue());
                verify(view).showInfo(captor.getValue());
                return null;
            }
        };
        doAnswer(answer).when(client).getReposForUser(anyString(), any(GitHubClient.OnChooserDataLoadedListener.class));
        presenter.onUserChosen(captor.getValue());
        verify(view).showInfo(captor.getValue());
    }

    @Test
    public void createPresenterObject_checkDoesViewIsNotNull() {
        ChooseRepoFragment view = ChooseRepoFragment.newInstance();
        ChooseFragmentPresenter presenter = new ChooseFragmentPresenter(view, client);
        assertNotNull(presenter.getView());
    }

    @Test
    public void onChosenUser_getReposForUserCalled() {
        presenter.onSearchUser("user");
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
        presenter.onSearchUser(null);
        verify(view).makeUserLoginIsNullToast();
    }

    @Test
    public void onResponse_called() {
    }

    @After
    public void tearDown() {
        presenter = null;
    }
}

