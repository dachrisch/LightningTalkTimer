package com.muckibude.cda.lightningtalktimer.countdown;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Locale;

public abstract class PauseableCountdownTimer {
    private static final String TAG = "PauseableCountdownTimer";

    private boolean isRunning = false;
    private long remainingTime;
    private long countDownInterval;
    private CountDownTimer actualCountDownTimer;

    public PauseableCountdownTimer(long millisInFuture, long countDownInterval) {
        this.remainingTime = millisInFuture;
        this.countDownInterval = countDownInterval;
    }

    public PauseableCountdownTimer start() {
        isRunning = true;
        actualCountDownTimer = createCountDownTimer().start();
        return this;
    }

    @NonNull
    private CountDownTimer createCountDownTimer() {
        return new CountDownTimer(this.remainingTime, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                PauseableCountdownTimer.this.onTick(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                PauseableCountdownTimer.this.onFinish();
            }
        };
    }


    public boolean isRunning() {
        return isRunning;
    }

    protected abstract void onFinish();

    public abstract void onTick(long millisUntilFinished);

    public void pause() {
        Log.d(TAG, "paused");
        isRunning = false;
        actualCountDownTimer.cancel();
    }

    public void resume() {
        Log.d(TAG, String.format(Locale.getDefault(), "resume(%d)", remainingTime));
        isRunning = true;
        actualCountDownTimer = createCountDownTimer().start();
    }
}
