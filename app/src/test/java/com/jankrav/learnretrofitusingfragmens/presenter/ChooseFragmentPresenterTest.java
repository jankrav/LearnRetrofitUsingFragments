package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChooseFragmentPresenterTest {
    private static String userName = "USER";
    private static String repoName = "REPO";
    @Mock
    ChoosePresenter presenter;
    @Mock
    GitHubClient client;

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

    @Test
    public void onUserChoosen_clientTransaction() throws Exception {
        presenter.onUserChosen(userName);
        verify(presenter).onUserChosen(userName);
    }

    @Test
    public void onDetachView_isCalled() throws Exception {
        presenter.onDetachView();
        verify(presenter).onDetachView();
    }
}

