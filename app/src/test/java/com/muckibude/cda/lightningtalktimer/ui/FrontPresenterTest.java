package com.muckibude.cda.lightningtalktimer.ui;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.BackModel;
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
        assertThat(incrementSeconds(17), is(30));
        assertThat(incrementSeconds(29), is(30));
        assertThat(incrementSeconds(30), is(45));
        assertThat(incrementSeconds(31), is(45));

    }

    @Test
    public void whenDecreasingNext15SecondsIntervalWillBeUsed() {
        assertThat(decrementSeconds(17), is(15));
        assertThat(decrementSeconds(16), is(15));
        assertThat(decrementSeconds(15), is(0));
        assertThat(decrementSeconds(14), is(0));
    }

    @Test
    public void whenDecreasing60SecondsNextMinuteWillBeDecreased() {
        FrontView view = mock(FrontView.class);
        CountdownEntity secondsEntity = new CountdownEntity(2, 15);
        FrontPresenter presenter = new FrontPresenter(new FrontModel(secondsEntity), new BackModel(secondsEntity));
        presenter.setView(view);
        presenter.decrease15Seconds();
        presenter.decrease15Seconds();
        presenter.decrease15Seconds();
        presenter.decrease15Seconds();
        assertThat(secondsEntity.getMinutes(), is(1));
        assertThat(secondsEntity.getSeconds(), is(15));
    }

    private int incrementSeconds(int seconds) {
        FrontView view = mock(FrontView.class);
        CountdownEntity secondsEntity = new CountdownEntity(1, seconds);
        FrontPresenter presenter = new FrontPresenter(new FrontModel(secondsEntity), new BackModel(secondsEntity));
        presenter.setView(view);
        presenter.increase15Seconds();
        return secondsEntity.getSeconds();
    }

    private int decrementSeconds(int seconds) {
        FrontView view = mock(FrontView.class);
        CountdownEntity secondsEntity = new CountdownEntity(1, seconds);
        FrontPresenter presenter = new FrontPresenter(new FrontModel(secondsEntity), new BackModel(secondsEntity));
        presenter.setView(view);
        presenter.decrease15Seconds();
        return secondsEntity.getSeconds();
    }
}