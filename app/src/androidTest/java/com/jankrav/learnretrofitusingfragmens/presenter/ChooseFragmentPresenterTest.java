package com.jankrav.learnretrofitusingfragmens.presenter;

import android.support.test.runner.AndroidJUnit4;

import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseFragmentView;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class ChooseFragmentPresenterTest {
    @Test
    public void onSelectedRepo_verifyCheckoutToDetail() {
        ChooseFragmentView view = mock(ChooseRepoFragment.class);
        ChoosePresenter presenter = mock(ChooseFragmentPresenter.class);
        view.setPresenter(presenter);
        presenter.onSelectedRepo("USER", "REPO");
        verify(view).checkoutToDetailFragment("USER", "REPO");
    }
}

