package com.jankrav.learnretrofitusingfragmens.presenter;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ChooseFragmentPresenterTest {
    private static String userName = "USER";
    private static String repoName = "REPO";

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


}