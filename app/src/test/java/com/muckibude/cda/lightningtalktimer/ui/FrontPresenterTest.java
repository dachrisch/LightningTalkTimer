package com.muckibude.cda.lightningtalktimer.ui;

import android.support.annotation.NonNull;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.ColorProvider;
import com.muckibude.cda.lightningtalktimer.domain.FrontModel;
import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;
import com.muckibude.cda.lightningtalktimer.presentation.FrontView;

import org.junit.Test;

import static com.muckibude.cda.lightningtalktimer.domain.ColorProvider.BLUE;
import static com.muckibude.cda.lightningtalktimer.domain.ColorProvider.GREEN;
import static com.muckibude.cda.lightningtalktimer.domain.ColorProvider.VIOLET;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        FrontPresenter presenter = createFrontPresenter(secondsEntity);
        presenter.setView(view);
        presenter.decrease();
        presenter.decrease();
        presenter.decrease();
        presenter.decrease();
        assertThat(secondsEntity, is(new CountdownEntity(1, 15)));

    }

    @Test
    public void above10MinutesMinutesAreDisplayed() {
        FrontView view = mock(FrontView.class);
        CountdownEntity secondsEntity = new CountdownEntity(9, 45);
        FrontPresenter presenter = createFrontPresenter(secondsEntity);
        presenter.setView(view);
        verify(view).display(9, 45);
        presenter.increase();
        assertThat(secondsEntity, is(new CountdownEntity(10, 0)));
        verify(view).display(10);
    }

    @Test
    public void above10MinutesMinutesAreIncremented() {
        FrontView view = mock(FrontView.class);
        CountdownEntity secondsEntity = new CountdownEntity(10, 00);
        FrontPresenter presenter = createFrontPresenter(secondsEntity);
        presenter.setView(view);
        verify(view).display(10);
        presenter.increase();
        assertThat(secondsEntity, is(new CountdownEntity(11, 0)));
        verify(view).display(11);
    }

    @Test
    public void above10MinutesMinutesAreDecremented() {
        FrontView view = mock(FrontView.class);
        CountdownEntity secondsEntity = new CountdownEntity(12, 00);
        FrontPresenter presenter = createFrontPresenter(secondsEntity);
        presenter.setView(view);
        verify(view).display(12);
        presenter.decrease();
        assertThat(secondsEntity, is(new CountdownEntity(11, 0)));
        verify(view).display(11);
    }

    @Test
    public void after10Minutes945willBeDisplayed() {
        FrontView view = mock(FrontView.class);
        CountdownEntity secondsEntity = new CountdownEntity(10, 00);
        FrontPresenter presenter = createFrontPresenter(secondsEntity);
        presenter.setView(view);
        verify(view).display(10);
        presenter.decrease();
        assertThat(secondsEntity, is(new CountdownEntity(9, 45)));
        verify(view).display(9, 45);
    }

    @Test
    public void toggleThroughColors() {
        FrontView view = mock(FrontView.class);

        CountdownEntity secondsEntity = new CountdownEntity(2, 15);
        FrontModel frontModel = new FrontModel(secondsEntity);
        frontModel.setColors(new ColorProvider());
        FrontPresenter presenter = new FrontPresenter(frontModel);
        presenter.setView(view);

        presenter.toggleColor();
        verify(view).switchPickerColorTo(BLUE);

        presenter.toggleColor();
        verify(view).switchPickerColorTo(VIOLET);

        presenter.toggleColor();
        verify(view).switchPickerColorTo(GREEN);
    }

    private int incrementSeconds(int seconds) {
        FrontView view = mock(FrontView.class);
        CountdownEntity secondsEntity = new CountdownEntity(1, seconds);
        FrontPresenter presenter = createFrontPresenter(secondsEntity);
        presenter.setView(view);
        presenter.increase();
        return secondsEntity.getSeconds();
    }

    private int decrementSeconds(int seconds) {
        FrontView view = mock(FrontView.class);
        CountdownEntity secondsEntity = new CountdownEntity(1, seconds);
        FrontPresenter presenter = createFrontPresenter(secondsEntity);
        presenter.setView(view);
        presenter.decrease();
        return secondsEntity.getSeconds();
    }

    @NonNull
    private FrontPresenter createFrontPresenter(CountdownEntity secondsEntity) {
        return new FrontPresenter(new FrontModel(secondsEntity));
    }
}
