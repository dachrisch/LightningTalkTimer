package com.muckibude.cda.lightningtalktimer.domain;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.Log;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

import java.util.Locale;

public class PausableOneSecondCountdownTimer implements PausableCountdownTimer {
    private static final String TAG = PausableOneSecondCountdownTimer.class.getName();
    private final CountdownEntity countdownEntity;
    private boolean isRunning = false;
    CountDownTimer countDownTimer;

    public PausableOneSecondCountdownTimer(@NonNull CountdownEntity countdownEntity) {
        this.countdownEntity = countdownEntity;

    }


    @Override
    public PausableCountdownTimer start() {
        isRunning = true;

        countDownTimer = new CountDownTimer(countdownEntity.inMillis(), 1000) {
            @Override
            public void onTick(long l) {
                onSecond();
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
        return this;
    }

    protected void onSecond() {
        if (isRunning) {
            Log.d(TAG, String.format(Locale.getDefault(), "onSecond: %s", countdownEntity));
            countdownEntity.decrementSeconds(1);
        }
    }

    @Override
    public void pause() {
        isRunning = false;
        countDownTimer.cancel();
    }

    @Override
    public void resume() {
        start();
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }


}
