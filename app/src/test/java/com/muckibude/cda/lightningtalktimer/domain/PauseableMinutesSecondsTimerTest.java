package com.muckibude.cda.lightningtalktimer.domain;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PauseableMinutesSecondsTimerTest {

    @Test
    public void whenTimerStartsItRunsTillEnd() {
        final CountdownEntity countdownEntity = new CountdownEntity(1, 0);
        PausableCountdownTimer countdownTimer = new PausableOneSecondCountdownTimer(countdownEntity);
        countdownTimer.start();
        assertThat(countdownEntity.getMinutes(), is(0));
        assertThat(countdownEntity.getSeconds(), is(0));
    }

    @Test
    public void everySecondEntityIsUpdated() {
        final CountdownEntity countdownEntity = new CountdownEntity(1, 0);
        PausableOneSecondCountdownTimer countdownTimer = new PausableOneSecondCountdownTimer(countdownEntity);
        countdownTimer.resume();
        countdownTimer.onSecond();
        assertThat(countdownEntity.getSeconds(), is(59));
        countdownTimer.onSecond();
        assertThat(countdownEntity.getSeconds(), is(58));
    }

    @Test
    public void pauseSavesTheActualState() {
        final CountdownEntity countdownEntity = new CountdownEntity(1, 0);
        PausableOneSecondCountdownTimer countdownTimer = new PausableOneSecondCountdownTimer(countdownEntity);
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

}
