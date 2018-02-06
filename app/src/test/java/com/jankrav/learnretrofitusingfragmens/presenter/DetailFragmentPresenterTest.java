package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailRepoFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DetailFragmentPresenterTest {
    @Mock
    GitHubClient client;
    @Mock
    DetailRepoFragment view;

    DetailFragmentPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new DetailFragmentPresenter(view, client);
    }

    @Test
    public void onDetachView_called() throws Exception {
        presenter.onDetachView();
        assertNull(presenter.getView());
    }

    @Test
    public void onSelectedRepo_calledGetRepoInfo() {
        presenter.onSelectedRepo("User", "Repo");
        verify(client).getRepoInfo("User", "Repo", presenter);
    }

    @Test
    public void onSelectedRepo_calledUserInfoFailureToast() {
        presenter.onSelectedRepo(null, null);
        verify(view).makeUserInfoFailureToast();
    }

    @Test
    public void onResponse_calledGoodToast() {
        GitHubRepo repo = new GitHubRepo();
        presenter.onResponse(repo);
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showInfo(repo);
        inOrder.verify(view).makeGoodToast();
    }

    @Test
    public void onResponse_calledRepoIsNullToast() {
        presenter.onResponse(null);
        verify(view).makeRepoIsNullToast();
    }

    @Test
    public void onFailure_calledFailureToast() {
        presenter.onFailure(new Throwable());
        verify(view).makeFailureToast();
    }
}


