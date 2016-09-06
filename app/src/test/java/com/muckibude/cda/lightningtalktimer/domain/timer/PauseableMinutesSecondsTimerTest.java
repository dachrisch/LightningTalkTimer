package com.muckibude.cda.lightningtalktimer.domain.timer;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PauseableMinutesSecondsTimerTest {

    @Test
    public void everySecondEntityIsUpdated() {
        final CountdownEntity countdownEntity = new CountdownEntity(1, 0);
        PausableOneSecondCountdownTimer countdownTimer = new PausableOneSecondCountdownTimer(countdownEntity);
        countdownTimer.resume();
        countdownTimer.onTick(59 * 1000);
        assertThat(countdownEntity.getSeconds(), is(59));
        countdownTimer.onTick(58 * 1000);
        assertThat(countdownEntity.getSeconds(), is(58));
    }

    @Test
    public void pauseSavesTheActualState() {
        final CountdownEntity countdownEntity = new CountdownEntity(1, 0);
        PausableOneSecondCountdownTimer countdownTimer = new PausableOneSecondCountdownTimer(countdownEntity);
        countdownTimer.resume();
        countdownTimer.onTick(59 * 1000);
        assertThat(countdownEntity.getSeconds(), is(59));
        countdownTimer.pause();
        countdownTimer.onTick(59 * 1000);
        assertThat(countdownEntity.getSeconds(), is(59));
        countdownTimer.resume();
        countdownTimer.onTick(58 * 1000);
        assertThat(countdownEntity.getSeconds(), is(58));
    }

    @Test
    public void near15SecondsIsRoundedTo15Seconds() {
        CountdownEntity countdownEntity = new CountdownEntity(0, 0);
        countdownEntity.updateFromMillis(14579);
        assertThat(countdownEntity, is(new CountdownEntity(0, 15)));
    }

    @Test
    public void nearMinuteisRoundedToNextMinute() {
        CountdownEntity countdownEntity = new CountdownEntity(0, 0);
        countdownEntity.updateFromMillis(59687);
        assertThat(countdownEntity, is(new CountdownEntity(1, 0)));
    }
}
