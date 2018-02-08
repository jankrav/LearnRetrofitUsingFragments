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

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DetailFragmentPresenterTest {
    @Mock
    GitHubClient client;
    @Mock
    DetailRepoFragment view;
    @Captor
    ArgumentCaptor<GitHubRepo> captor;

    DetailFragmentPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new DetailFragmentPresenter(view, client);
    }

    @Test
    public void someTest() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                final GitHubClient.OnDetailDataLoadedListener listener = invocation.getArgument(2);
                listener.onSuccess(new GitHubRepo());
                verify(listener).onSuccess(captor.capture());

                return null;
            }
        }).when(client).getRepoInfo(anyString(),anyString(), any(GitHubClient.OnDetailDataLoadedListener .class));
        presenter.onGetInfoFromServer(captor.getValue());
        verify(view).showInfo(captor.getValue());

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

    @Test
    public void onResponse_calledGoodToast() {

    }

    @Test
    public void onResponse_calledRepoIsNullToast() {

    }

    @Test
    public void onFailure_calledFailureToast() {

    }

    @After
    public void tearDown(){
        presenter = null;
    }
}


