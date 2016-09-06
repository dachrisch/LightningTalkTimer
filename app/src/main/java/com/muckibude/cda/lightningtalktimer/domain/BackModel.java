package com.muckibude.cda.lightningtalktimer.domain;

import android.util.Log;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.data.EntityChangeListener;
import com.muckibude.cda.lightningtalktimer.domain.timer.NotStartableCountdownTimer;
import com.muckibude.cda.lightningtalktimer.domain.timer.OnFinishedListener;
import com.muckibude.cda.lightningtalktimer.domain.timer.PausableCountdownTimer;
import com.muckibude.cda.lightningtalktimer.domain.timer.PausableCountdownTimerBuilder;

import javax.inject.Inject;

public class BackModel {

    private static final String TAG = BackModel.class.getName();
    private Integer backgroundColor = 0;
    private final PausableCountdownTimerBuilder pausableCountdownTimerBuilder;
    private PausableCountdownTimer countdownTimer = new NotStartableCountdownTimer();
    private CountdownEntity startCountdown;

    private EntityChangeListener entityChangeListener;
    private OnFinishedListener onFinishedListener;


    @Inject
    public BackModel(PausableCountdownTimerBuilder pausableCountdownTimerBuilder) {
        this.pausableCountdownTimerBuilder = pausableCountdownTimerBuilder;
    }


    public void setBackgroundColor(Integer backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getBackgroundColor() {
        return backgroundColor;
    }

    public void startCountdown() {
        Log.d(TAG, "start timer with: " + startCountdown);

        CountdownEntity runningCountdown = new CountdownEntity(startCountdown.getMinutes(), startCountdown.getSeconds());
        runningCountdown.registerEntityChangeListener(entityChangeListener);

        countdownTimer = pausableCountdownTimerBuilder.build(runningCountdown);
        countdownTimer.registerOnFinishedListener(onFinishedListener);

        countdownTimer.start();
    }

    public boolean toggleTimer() {

        if (countdownTimer.isRunning()) {
            countdownTimer.pause();
        } else {
            countdownTimer.resume();
        }

        return countdownTimer.isRunning();
    }

    public void stopTimer() {
        if (null != countdownTimer) {
            countdownTimer.pause();
            countdownTimer = null;
        }
    }

    public void registerEntityChangeListener(EntityChangeListener entityChangeListener) {
        this.entityChangeListener = entityChangeListener;
    }

    public void setStartCountdown(CountdownEntity startCountdown) {
        this.startCountdown = startCountdown;
    }

    public void registerOnFinishedListener(OnFinishedListener onFinishedListener) {
        this.onFinishedListener = onFinishedListener;
    }
}

