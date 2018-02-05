package com.jankrav.learnretrofitusingfragmens.presenter;

import android.support.test.runner.AndroidJUnit4;

import com.jankrav.learnretrofitusingfragmens.model.client.GitHubClient;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class ChooseFragmentPresenterTest {
//    @Spy
    ChooseGitHubClient client;
//    @Spy

    ChoosePresenter presenter;
//    @Spy
    @Mock
    ChooseFragmentView view;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
//        presenter = new ChooseFragmentPresenter();
        client  = new GitHubClient();
        presenter.setClient(client);
        view = new ChooseRepoFragment();
        view.setPresenter(presenter);
    }

    @Test
    public void onSelectedRepo_verifyCheckoutToDetail() {
//        presenter.onSelectedRepo("USER", "REPO");
//        verify(view).checkoutToDetailFragment("USER","REPO");
    }
}

