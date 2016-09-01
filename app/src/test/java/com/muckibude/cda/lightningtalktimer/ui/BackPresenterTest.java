package com.muckibude.cda.lightningtalktimer.ui;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.PauseableMinutesSecondsTimer;
import com.muckibude.cda.lightningtalktimer.presentation.BackPresenter;
import com.muckibude.cda.lightningtalktimer.presentation.BackView;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BackPresenterTest {

    @Test
    public void displayMinutesBigIfAboveOneMinute() {
        BackView backView = mock(BackView.class);
        PauseableMinutesSecondsTimer timer = mock(PauseableMinutesSecondsTimer.class);
        BackPresenter backPresenter = new BackPresenter(new BackModel(new CountdownEntity(1, 1)), timer);
        backPresenter.setView(backView);
        backPresenter.onSecond(60 * 1000);
        verify(backView).display(1, 0);
    }

    @Test
    public void displaySecondsBigIfBelowOneMinute() {
        BackView backView = mock(BackView.class);
        PauseableMinutesSecondsTimer timer = mock(PauseableMinutesSecondsTimer.class);
        BackPresenter backPresenter = new BackPresenter(new BackModel(new CountdownEntity(1, 0)), timer);
        backPresenter.setView(backView);
        backPresenter.onSecond(59 * 1000);
        verify(backView).display(59);
    }

    @Test
    public void cancelTimerWhenStopTimer() {
        PauseableMinutesSecondsTimer timer = mock(PauseableMinutesSecondsTimer.class);
        BackPresenter backPresenter = new BackPresenter(new BackModel(new CountdownEntity(1, 0)), timer);
        backPresenter.stopTimer();
        verify(timer).cancel();
    }

    @Test
    public void cancelTimerOnViewDestroyed() {
        PauseableMinutesSecondsTimer timer = mock(PauseableMinutesSecondsTimer.class);
        BackCountdownDisplayFragment fragment = new BackCountdownDisplayFragment();
        fragment.backPresenter = new BackPresenter(new BackModel(new CountdownEntity(1, 0)), timer);
        fragment.onDestroyView();
        verify(timer).cancel();
    }


}
