package com.jankrav.learnretrofitusingfragmens.presenter;

import com.jankrav.learnretrofitusingfragmens.model.GitHubRepo;
import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChooseFragmentPresenterTest {
    private static String userName = "USER";
    private static String repoName = "REPO";


    @Mock
    private ChooseFragmentView view;

    private ChooseFragmentPresenter presenter ;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new ChooseFragmentPresenter(view);
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
    public void testRequestResponse() throws Exception{

    }

}