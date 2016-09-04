package com.muckibude.cda.lightningtalktimer.domain;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PauseableMinutesSecondsTimerTest {

    @Test
    public void whenTimerStartsItRunsTillEnd() {
        final CountdownEntity countdownEntity = new CountdownEntity(1, 0);
        PausableCountdownTimer countdownTimer = new PausableCountdownTimer(countdownEntity);
        countdownTimer.setTickGiver(new TickGiver() {
            @Override
            public void waitOneSecond() {
                // pass
            }
        });
        countdownTimer.start();
        assertThat(countdownEntity.getMinutes(), is(0));
        assertThat(countdownEntity.getSeconds(), is(0));
    }

    @Test
    public void everySecondEntityIsUpdated() {
        final CountdownEntity countdownEntity = new CountdownEntity(1, 0);
        PausableCountdownTimer countdownTimer = new PausableCountdownTimer(countdownEntity);
        countdownTimer.resume();
        countdownTimer.onSecond();
        assertThat(countdownEntity.getSeconds(), is(59));
        countdownTimer.onSecond();
        assertThat(countdownEntity.getSeconds(), is(58));
    }

    @Test
    public void pauseSavesTheActualState() {
        final CountdownEntity countdownEntity = new CountdownEntity(1, 0);
        PausableCountdownTimer countdownTimer = new PausableCountdownTimer(countdownEntity);
        countdownTimer.resume();
        countdownTimer.onSecond();
        assertThat(countdownEntity.getSeconds(), is(59));
        countdownTimer.pause();
        countdownTimer.onSecond();
        assertThat(countdownEntity.getSeconds(), is(59));
        countdownTimer.resume();
        countdownTimer.onSecond();
        assertThat(countdownEntity.getSeconds(), is(58));
    }

    @Test
    public void tickIsGivenByExternalResource() {
        final CountdownEntity countdownEntity = new CountdownEntity(1, 0);
        PausableCountdownTimer countdownTimer = new PausableCountdownTimer(countdownEntity);
        TickGiver tickGiver = mock(TickGiver.class);
        countdownTimer.setTickGiver(tickGiver);
        countdownTimer.start();
        verify(tickGiver, times(60)).waitOneSecond();
    }

}
