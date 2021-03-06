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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
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

    @Captor
    ArgumentCaptor<GitHubClient.OnChooserDataLoadedListener> interfaceCaptor;

    @Captor
    ArgumentCaptor<List<GitHubRepo>> reposCaptor;

    private ChooseFragmentPresenter presenter;
    final String login = "User";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ChooseFragmentPresenter(view, client);
    }
//    async task invoke onSuccess
    @Test
    public void testOnSearchUser_getResponseFromServerUsingAnswer()throws Throwable {
        final List<GitHubRepo> results = new ArrayList<>();
        Answer answer = new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final GitHubClient.OnChooserDataLoadedListener listener = invocation.getArgument(1);
                listener.onSuccess(results);
                return null;
            }
        };
        doAnswer(answer).when(client).getReposForUser(anyString(), any(GitHubClient.OnChooserDataLoadedListener.class));
        presenter.onSearchUser(login);
        verify(client, times(1))
                .getReposForUser(anyString(),
                        any(GitHubClient.OnChooserDataLoadedListener.class));
        verify(view).showInfo(reposCaptor.capture());
        assertThat(results, is(equalTo(reposCaptor.getValue())));
    }
    @Test
    public void testOnSearchUser_getResponseFromServerUsingCaptor()throws Throwable {
        final List<GitHubRepo> results = new ArrayList<>();

        presenter.onSearchUser(login);
        verify(client).getReposForUser(anyString(), interfaceCaptor.capture());
        interfaceCaptor.getValue().onSuccess(results);
        verify(view).showInfo(reposCaptor.capture());
        assertThat(reposCaptor.getValue(), is(equalTo(results)));
    }

//  async task invoke onFailure()
    @Test
    public void testOnSearchUser_getFailureFromServerUsingAnswer()throws Throwable {
        final Throwable result = new Throwable();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final GitHubClient.OnChooserDataLoadedListener listener = invocation.getArgument(1);
                listener.onFailure(result);
                return null;
            }
        }).when(client).getReposForUser(anyString(), any(GitHubClient.OnChooserDataLoadedListener.class));
        presenter.onSearchUser(login);
        verify(client).getReposForUser(anyString(), any(GitHubClient.OnChooserDataLoadedListener.class));
        verify(view).makeFailureToast();
    }
    @Test
    public void testOnSearchUser_getFailureFromServerUsingCaptor()throws Throwable {
        final Throwable result = new Throwable();
        presenter.onSearchUser(login);
        verify(client).getReposForUser(anyString(), interfaceCaptor.capture());
        interfaceCaptor.getValue().onFailure(result);
        verify(view).makeFailureToast();
    }

//  async task invoke onSuccess() with null object
    @Test
    public void testOnSearchUser_getResponseNullRepoFromServerUsingAnswer() throws Throwable{
        final List<GitHubRepo> result = null;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final GitHubClient.OnChooserDataLoadedListener listener = invocation.getArgument(1);
                listener.onSuccess(result);
                return null;
            }
        }).when(client).getReposForUser(anyString(),any(GitHubClient.OnChooserDataLoadedListener.class));
        presenter.onSearchUser(login);
        verify(view).makeReposIsNullToast();
    }
    @Test
    public void testOnSearchUser_getResponseNullRepoFromServerUsingCaptor() throws Throwable {
        final List<GitHubRepo> result = null;
        presenter.onSearchUser(login);
        verify(client, times(1)).getReposForUser(anyString(), interfaceCaptor.capture());
        interfaceCaptor.getValue().onSuccess(result);
        verify(view).makeReposIsNullToast();
    }

    @Test
    public void createPresenterObject_checkDoesViewIsNotNull() throws Exception {
        ChooseRepoFragment view = ChooseRepoFragment.newInstance();
        ChooseFragmentPresenter presenter = new ChooseFragmentPresenter(view, client);
        assertNotNull(presenter.getView());
    }

    @Test
    public void onSelectedRepo_callCheckOutToOtherFragment() throws Exception {
        presenter.onSelectedRepo("user", "repo");
        verify(view).checkoutToDetailFragment("user", "repo");
    }

    @Test
    public void onDetachView_called() throws Exception {
        presenter.onDetachView();
        assertNull(presenter.getView());
    }

    @Test
    public void onUserChosen_userLoginIsEmpty() throws Exception {
        presenter.onSearchUser(null);
        verify(view).makeUserLoginIsNullToast();
    }

    @After
    public void tearDown() {
        presenter = null;
    }
}

