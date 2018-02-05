package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.DetailRepoFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DetailFragmentPresenterTest {
    @Test public void onAttachView_called(){
        DetailFragmentPresenter presenter = mock(DetailFragmentPresenter.class);
        DetailRepoFragment view = new DetailRepoFragment();
        view.setPresenter(presenter);
        verify(presenter).onAttachView(view);
    }
    @Test public void onDetachView_called(){
        DetailFragmentPresenter presenter = mock(DetailFragmentPresenter.class);
        DetailRepoFragment view = new DetailRepoFragment();
        view.setPresenter(presenter);
        view.onDetach();
        verify(presenter).onDetachView();
    }
    @Test public void onSelectedRepo_calledGetRepoInfo(){

        GitHubClient client = mock(GitHubClient.class);
        DetailRepoFragment view =  mock(DetailRepoFragment.class);
        DetailFragmentPresenter presenter = new DetailFragmentPresenter();
        view.setPresenter(presenter);
        presenter.setClient(client);

        presenter.onSelectedRepo("User", "Repo");
        verify(client).getRepoInfo("User", "Repo", presenter);
    }
    @Test public void onResponse_calledRepoIsNullToast(){
        DetailRepoFragment view = mock(DetailRepoFragment.class);
        GitHubClient client = mock(GitHubClient.class);
        DetailFragmentPresenter presenter = new DetailFragmentPresenter();

        presenter.setClient(client);
        presenter.setView(view);
        presenter.onResponse(null);
        verify(view).makeRepoIsNullToast();
    }
    @Test public void onFailure_calledFailureToast(){
        DetailRepoFragment view = mock(DetailRepoFragment.class);
        GitHubClient client = mock(GitHubClient.class);
        DetailFragmentPresenter presenter = new DetailFragmentPresenter();
        presenter.setClient(client);
        presenter.setView(view);

        presenter.onFailure(new Throwable());
        verify(view).makeFailureToast();
    }
    @Test public void onResponse_calledGoodToast(){
        DetailRepoFragment view = mock(DetailRepoFragment.class);
        GitHubClient client = mock(GitHubClient.class);
        DetailFragmentPresenter presenter = new DetailFragmentPresenter();
        presenter.setClient(client);
        presenter.setView(view);

        GitHubRepo testObject = new GitHubRepo();
        presenter.onResponse(testObject);
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showInfo(testObject);
        inOrder.verify(view).makeGoodToast();

    }
}


