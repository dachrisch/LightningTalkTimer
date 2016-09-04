package com.muckibude.cda.lightningtalktimer.ui;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.PausableCountdownTimer;
import com.muckibude.cda.lightningtalktimer.domain.TickGiver;
import com.muckibude.cda.lightningtalktimer.presentation.BackPresenter;
import com.muckibude.cda.lightningtalktimer.presentation.BackView;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BackPresenterTest {

    @Test
    public void displayMinutesBigIfAboveOneMinute() {
        BackView backView = mock(BackView.class);
        PausableCountdownTimer timer = new PausableCountdownTimer(new CountdownEntity(1, 0));
        timer.setTickGiver(new TickGiver() {
            @Override
            public void waitOneSecond() {

            }
        });
        BackPresenter backPresenter = new BackPresenter(new BackModel(new CountdownEntity(1, 1)), timer);
        backPresenter.setView(backView);
        backPresenter.startTimer();
        verify(backView).display(1, 0);
    }

    @Test
    public void displaySecondsBigIfBelowOneMinute() {
        BackView backView = mock(BackView.class);
        PausableCountdownTimer timer = new PausableCountdownTimer(new CountdownEntity(1, 0));
        timer.setTickGiver(new TickGiver() {
            @Override
            public void waitOneSecond() {

            }
        });
        BackPresenter backPresenter = new BackPresenter(new BackModel(new CountdownEntity(1, 0)), timer);
        backPresenter.setView(backView);
        backPresenter.startTimer();
        verify(backView).display(59);
    }

    @Test
    public void cancelTimerWhenStopTimer() {
        PausableCountdownTimer timer = mock(PausableCountdownTimer.class);
        BackPresenter backPresenter = new BackPresenter(new BackModel(new CountdownEntity(1, 0)), timer);
        backPresenter.stopTimer();
        verify(timer).pause();
    }

    @Test
    public void cancelTimerOnViewDestroyed() {
        PausableCountdownTimer timer = mock(PausableCountdownTimer.class);
        BackCountdownDisplayFragment fragment = new BackCountdownDisplayFragment();
        fragment.backPresenter = new BackPresenter(new BackModel(new CountdownEntity(1, 0)), timer);
        fragment.onDestroyView();
        verify(timer).pause();
    }


}
