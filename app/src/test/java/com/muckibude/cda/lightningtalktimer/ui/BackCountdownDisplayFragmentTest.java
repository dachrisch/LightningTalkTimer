package com.muckibude.cda.lightningtalktimer.ui;

import com.muckibude.cda.lightningtalktimer.data.MinutesSecondsEntity;
import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.PauseableMinutesSecondsTimer;
import com.muckibude.cda.lightningtalktimer.presentation.BackPresenter;
import com.muckibude.cda.lightningtalktimer.presentation.BackView;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BackCountdownDisplayFragmentTest {

    @Test
    public void displayMinutesBigIfAboveOneMinute() {
        BackView backView = mock(BackView.class);

        PauseableMinutesSecondsTimer timer = mock(PauseableMinutesSecondsTimer.class);

        BackPresenter backPresenter = new BackPresenter(new BackModel(new MinutesSecondsEntity(1, 1)), timer);
        backPresenter.setView(backView);
        backPresenter.onSecond();

        verify(backView).display(1, 0);
    }

    @Test
    public void displaySecondsBigIfBelowOneMinute() {
        BackView backView = mock(BackView.class);

        PauseableMinutesSecondsTimer timer = mock(PauseableMinutesSecondsTimer.class);

        BackPresenter backPresenter = new BackPresenter(new BackModel(new MinutesSecondsEntity(1, 0)), timer);
        backPresenter.setView(backView);
        backPresenter.onSecond();

        verify(backView).display(59);
    }
}