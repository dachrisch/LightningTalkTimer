package com.muckibude.cda.lightningtalktimer.domain;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.Locale;

public class PauseableMinutesSecondsTimer {
    private static final String TAG = "PauseableMinutesSeconds";
    private CountDownTimer countDownTimer;
    private int startMinutes;
    private int startSeconds;
    private OnSecondCallback onSecondsCallback;
    private OnFinishCallback onFinishCallback;

    public boolean isRunning() {
        return isRunning;
    }

    private boolean isRunning = false;

    public void start() {
        countDownTimer = new CountDownTimer(inMillis(), 500) {
            @Override
            public void onTick(long millisRemaining) {
                if (onSecondsCallback != null) {
                    Log.d(TAG, String.format(Locale.getDefault(), "remaining millis: %d", millisRemaining));
                    onSecondsCallback.onSecond(millisRemaining);
                }
            }

            @Override
            public void onFinish() {
                if (onFinishCallback != null) {
                    onFinishCallback.onFinish();
                    isRunning = false;
                    startMinutes = 0;
                    startSeconds = 0;
                }
            }
        }.start();
        isRunning = true;
    }

    public void setStartMinutes(int minutes) {
        this.startMinutes = minutes;
    }

    public void setStartSeconds(int seconds) {
        this.startSeconds = seconds;
    }

    private long inMillis() {
        return (startMinutes * 60 + startSeconds) * 1000;
    }

    public void setOnSecondsCallback(OnSecondCallback onSecondsCallback) {
        this.onSecondsCallback = onSecondsCallback;
    }

    public void setOnFinishCallback(OnFinishCallback onFinishCallback) {
        this.onFinishCallback = onFinishCallback;
    }

    public void pause() {
        countDownTimer.cancel();
        isRunning = false;
    }

    public void resume() {
        start();
    }

    public void cancel() {
        pause();
    }

    public interface OnSecondCallback {
        void onSecond(long millisRemaining);
    }

    public interface OnFinishCallback {
        void onFinish();
    }

}
