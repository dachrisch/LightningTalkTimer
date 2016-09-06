package com.muckibude.cda.lightningtalktimer.ui;

import android.support.annotation.NonNull;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.timer.PausableCountdownTimer;
import com.muckibude.cda.lightningtalktimer.domain.timer.PausableCountdownTimerBuilder;
import com.muckibude.cda.lightningtalktimer.domain.timer.PausableOneSecondCountdownTimer;
import com.muckibude.cda.lightningtalktimer.presentation.BackPresenter;
import com.muckibude.cda.lightningtalktimer.presentation.BackView;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BackPresenterTest {

    @Test
    public void displayMinutesBigIfAboveOneMinute() {
        BackView backView = mock(BackView.class);
        BackPresenter backPresenter = getBackPresenter(noDelayTimerBuilder(), new CountdownEntity(1, 1));
        backPresenter.setView(backView);
        backPresenter.startTimer();
        verify(backView).display(1, 0);
    }

    @NonNull
    private PausableCountdownTimerBuilder noDelayTimerBuilder() {
        return new PausableCountdownTimerBuilder() {
            @Override
            public PausableCountdownTimer build(CountdownEntity initialCountdownEntity) {
                initialCountdownEntity.decrementSeconds(1);
                return super.build(initialCountdownEntity);
            }
        };
    }

    @Test
    public void displaySecondsBigIfBelowOneMinute() {
        BackView backView = mock(BackView.class);
        BackPresenter backPresenter = getBackPresenter(noDelayTimerBuilder(), new CountdownEntity(1, 0));
        backPresenter.setView(backView);
        backPresenter.startTimer();
        verify(backView).display(59);
    }

    @Test
    public void cancelTimerWhenStopTimer() {
        final PausableCountdownTimer timer = mock(PausableOneSecondCountdownTimer.class);
        PausableCountdownTimerBuilder builder = new PausableCountdownTimerBuilder() {
            @Override
            public PausableCountdownTimer build(CountdownEntity initialCountdownEntity) {
                return timer;
            }
        };

        BackPresenter backPresenter = getBackPresenter(builder, new CountdownEntity(1, 0));
        backPresenter.startTimer();
        backPresenter.stopTimer();
        verify(timer).pause();
    }

    @NonNull
    private BackPresenter getBackPresenter(PausableCountdownTimerBuilder builder, CountdownEntity countdownEntity) {
        BackModel backModel = new BackModel(builder);
        backModel.setStartCountdown(countdownEntity);
        return new BackPresenter(backModel);
    }

    @Test
    public void cancelTimerOnViewDestroyed() {
        final PausableCountdownTimer timer = mock(PausableOneSecondCountdownTimer.class);
        PausableCountdownTimerBuilder builder = new PausableCountdownTimerBuilder() {
            @Override
            public PausableCountdownTimer build(CountdownEntity initialCountdownEntity) {
                return timer;
            }
        };
        BackCountdownDisplayFragment fragment = new BackCountdownDisplayFragment();
        fragment.backPresenter = getBackPresenter(builder, new CountdownEntity(1, 0));
        fragment.backPresenter.startTimer();
        fragment.onDestroyView();
        verify(timer).pause();
    }


}
