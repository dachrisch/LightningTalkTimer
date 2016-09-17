package com.muckibude.cda.lightningtalktimer.domain.timer;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.Log;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

import java.util.Locale;

public class PausableOneSecondCountdownTimer implements PausableCountdownTimer {
    private static final String TAG = PausableOneSecondCountdownTimer.class.getName();
    private final CountdownEntity countdownEntity;
    private boolean isRunning = false;
    private CountDownTimer countDownTimer;
    private OnFinishedListener onFinishedListener;

    public PausableOneSecondCountdownTimer(@NonNull CountdownEntity countdownEntity) {
        this.countdownEntity = countdownEntity;
    }

    @Override
    public PausableCountdownTimer start() {
        isRunning = true;

        countDownTimer = new CountDownTimer(countdownEntity.inMillis(), 333) {
            @Override
            public void onTick(long millisRemaining) {
                PausableOneSecondCountdownTimer.this.onTick(millisRemaining);
            }

            @Override
            public void onFinish() {
                PausableOneSecondCountdownTimer.this.onTick(0);
                pause();
                if (null != onFinishedListener) {
                    onFinishedListener.informFinished();
                }
            }
        };
        countDownTimer.start();
        return this;
    }

    protected void onTick(long millisRemaining) {
        if (isRunning) {
            Log.d(TAG, String.format(Locale.getDefault(), "remaining millis: %d", millisRemaining));
            countdownEntity.updateFromMillis(millisRemaining);
            Log.d(TAG, String.format(Locale.getDefault(), "onTick: %s", countdownEntity));
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

    @Override
    public void registerOnFinishedListener(OnFinishedListener onFinishedListener) {
        this.onFinishedListener = onFinishedListener;
    }

}
