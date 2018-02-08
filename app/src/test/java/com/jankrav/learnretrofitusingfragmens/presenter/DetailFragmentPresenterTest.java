package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailRepoFragment;

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


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DetailFragmentPresenterTest {
    @Mock
    GitHubClient client;
    @Mock
    DetailRepoFragment view;

    @Captor
    ArgumentCaptor<GitHubRepo> repoCaptor;
    @Captor
    ArgumentCaptor<GitHubClient.OnDetailDataLoadedListener> interfaceCaptor;

    private DetailFragmentPresenter presenter;
    private final String login = "User";
    private final String repoName = "Repo";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new DetailFragmentPresenter(view, client);
    }

    @Test
    public void testOnSelectedRepo_getResponseFromServerUsingAnswer() {
        final GitHubRepo result = new GitHubRepo();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final GitHubClient.OnDetailDataLoadedListener listener = invocation.getArgument(2);
                listener.onSuccess(result);
                return null;
            }
        }).when(client).getRepoInfo(anyString(), anyString(), any(GitHubClient.OnDetailDataLoadedListener.class));

        presenter.onSelectedRepo(login, repoName);
        verify(client, times(1)).getRepoInfo(anyString(), anyString(), any(GitHubClient.OnDetailDataLoadedListener.class));
        verify(view).showInfo(repoCaptor.capture());
        assertThat(result, is(equalTo(repoCaptor.getValue())));

    }
    @Test
    public void testOnSelectedRepo_getResponseFromServerUsingCaptor() throws Throwable{
        final GitHubRepo result = new GitHubRepo();
        presenter.onSelectedRepo(login, repoName);
        verify(client, times(1)).getRepoInfo(anyString(), anyString(), interfaceCaptor.capture());
        interfaceCaptor.getValue().onSuccess(result);
        verify(view).showInfo(repoCaptor.capture());
        assertThat(repoCaptor.getValue(), is(equalTo(result)));
    }

    @Test
    public void onDetachView_called() throws Exception {
        presenter.onDetachView();
        assertNull(presenter.getView());
    }

    @Test
    public void onSelectedRepo_calledGetRepoInfo() {
        presenter.onSelectedRepo("User", "Repo");
        verify(client).getRepoInfo(anyString(), anyString(), any(GitHubClient.OnDetailDataLoadedListener.class));
    }

    @Test
    public void onSelectedRepo_calledUserInfoFailureToast() {
        presenter.onSelectedRepo(null, null);
        verify(view).makeUserInfoFailureToast();
    }

    @After
    public void tearDown() {
        presenter = null;
    }
}


