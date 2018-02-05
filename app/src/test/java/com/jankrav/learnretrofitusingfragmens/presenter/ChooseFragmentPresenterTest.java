package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChooseFragmentPresenterTest {
    private static String userName = "USER";
    private static String repoName = "REPO";
    @Mock
    ChoosePresenter presenter;
    @Mock
    ChooseGitHubClient client;

    ChooseFragmentView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter.setClient(client);
        view = new ChooseRepoFragment();
        view.setPresenter(presenter);
    }

    @Test
    public void onAttachView_isCalled() throws Exception {
        verify(presenter).onAttachView(view);
    }


    @Test(expected = NullPointerException.class)
    public void onUserChosen_NullPointerException() throws Exception {
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);

        doThrow(new NullPointerException()).when(presenter).onUserChosen("");
        presenter.onUserChosen("");
        doThrow(new NullPointerException()).when(presenter).onUserChosen(null);
        presenter.onUserChosen(null);
    }

    @Test(expected = NullPointerException.class)
    public void onSelectedRepo_NullPointerException() throws Exception {
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);

        doThrow(new NullPointerException()).when(presenter).onSelectedRepo(null, null);
        presenter.onSelectedRepo(null, null);
        doThrow(new NullPointerException()).when(presenter).onSelectedRepo(userName, null);
        presenter.onSelectedRepo(userName, null);
        doThrow(new NullPointerException()).when(presenter).onSelectedRepo(null, repoName);
        presenter.onSelectedRepo(null, repoName);
        doThrow(new NullPointerException()).when(presenter).onSelectedRepo("", repoName);
        presenter.onSelectedRepo("", repoName);
        doThrow(new NullPointerException()).when(presenter).onSelectedRepo(userName, "");
        presenter.onSelectedRepo(userName, "");
    }
    
    @Test
    public void onUserChoosen_clientTransaction() throws Exception{
        presenter.onUserChosen(userName);
        verify(presenter).onUserChosen(userName);
        client.getReposForUser(presenter, userName);
        verify(client).getReposForUser(presenter, userName);
    }

    @Test
    public void onDetachView_isCalled() throws Exception {
        presenter.onDetachView();
        verify(presenter).onDetachView();
    }
}

