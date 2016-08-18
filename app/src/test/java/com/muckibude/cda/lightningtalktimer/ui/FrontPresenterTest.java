package com.muckibude.cda.lightningtalktimer.ui;

import android.support.annotation.NonNull;

import com.muckibude.cda.lightningtalktimer.data.MinutesSecondsEntity;
import com.muckibude.cda.lightningtalktimer.domain.FrontModel;
import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;
import com.muckibude.cda.lightningtalktimer.presentation.FrontView;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class FrontPresenterTest {

    @Test
    public void whenIncreasingNext15SecondsIntervalWillBeUsed() {
        assertThat(incrementSeconds(17).getSeconds(), is(30));
        assertThat(incrementSeconds(29).getSeconds(), is(30));
        assertThat(incrementSeconds(30).getSeconds(), is(45));
        assertThat(incrementSeconds(31).getSeconds(), is(45));

    }

    @Test
    public void whenDecreasingNext15SecondsIntervalWillBeUsed() {
        assertThat(decrementSeconds(17).getSeconds(), is(15));
        assertThat(decrementSeconds(16).getSeconds(), is(15));
        assertThat(decrementSeconds(15).getSeconds(), is(0));
        assertThat(decrementSeconds(14).getSeconds(), is(0));
    }

    @NonNull
    private MinutesSecondsEntity incrementSeconds(int seconds) {
        FrontView view = mock(FrontView.class);
        MinutesSecondsEntity secondsEntity = new MinutesSecondsEntity(1, seconds);
        FrontPresenter presenter = new FrontPresenter(new FrontModel(secondsEntity));
        presenter.setView(view);
        presenter.increase15Seconds();
        return secondsEntity;
    }

    @NonNull
    private MinutesSecondsEntity decrementSeconds(int seconds) {
        FrontView view = mock(FrontView.class);
        MinutesSecondsEntity secondsEntity = new MinutesSecondsEntity(1, seconds);
        FrontPresenter presenter = new FrontPresenter(new FrontModel(secondsEntity));
        presenter.setView(view);
        presenter.decrease15Seconds();
        return secondsEntity;
    }
}
