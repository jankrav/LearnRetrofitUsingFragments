package com.jankrav.learnretrofitusingfragmens;

import android.support.test.runner.AndroidJUnit4;

import com.jankrav.learnretrofitusingfragmens.presenter.ChooseFragmentPresenter;
import com.jankrav.learnretrofitusingfragmens.view.fragments.ChooseRepoFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;


@RunWith(AndroidJUnit4.class)
public class TestChoosePresenter {
    @Test(expected = NullPointerException.class)
    public void testOnUserChosenByNullPointerException(){
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);
        doThrow(new NullPointerException()).when(presenter).onUserChosen(null);
        presenter.onUserChosen(null);
    }
    @Test
    public void testOnSelectedRepoIdIsNotValid(){
        ChooseFragmentPresenter presenter = mock(ChooseFragmentPresenter.class);
//        doReturn()
    }
}
