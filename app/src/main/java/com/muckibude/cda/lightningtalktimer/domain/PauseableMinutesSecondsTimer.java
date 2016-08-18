package com.muckibude.cda.lightningtalktimer.domain;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.Locale;

public class PauseableMinutesSecondsTimer {
    private static final String TAG = "PauseableMinutesSeconds";
    private CountDownTimer countDownTimer;
    private int minutes;
    private int seconds;
    private OnSecondCallback onSecondsCallback;
    private OnFinishCallback onFinishCallback;

    public boolean isRunning() {
        return isRunning;
    }

    private boolean isRunning = false;

    public PauseableMinutesSecondsTimer start() {
        countDownTimer = new CountDownTimer(inMillis(), 1000) {
            @Override
            public void onTick(long l) {
                if (onSecondsCallback != null) {
                    Log.d(TAG, String.format(Locale.getDefault(), "remaining millis: %d", l));
                    onSecondsCallback.onSecond();
                }
            }

            @Override
            public void onFinish() {
                if (onFinishCallback != null) {
                    onFinishCallback.onFinish();
                    isRunning = false;
                }
            }
        }.start();
        isRunning = true;
        return this;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    private long inMillis() {
        return (minutes * 60 + seconds) * 1000;
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

    public static TimeBuilder doEverySecond(OnSecondCallback doEverySecond) {
        return new TimeBuilder(doEverySecond);
    }

    public void cancel() {
        pause();
    }

    public interface OnSecondCallback {
        void onSecond();
    }

    public interface OnFinishCallback {
        void onFinish();
    }

    public static class TimeBuilder {

        private PauseableMinutesSecondsTimer pauseableMinutesSecondsTimer = new PauseableMinutesSecondsTimer();

        public TimeBuilder(OnSecondCallback doEverySecond) {
            pauseableMinutesSecondsTimer.setOnSecondsCallback(doEverySecond);

        }


        public OnFinishBuilder startAt(int minutes, int seconds) {
            return new OnFinishBuilder(pauseableMinutesSecondsTimer).minutes(minutes).seconds(seconds);
        }

    }

    public static class OnFinishBuilder {
        private final PauseableMinutesSecondsTimer pauseableMinutesSecondsTimer;

        public OnFinishBuilder(PauseableMinutesSecondsTimer pauseableMinutesSecondsTimer) {
            this.pauseableMinutesSecondsTimer = pauseableMinutesSecondsTimer;
        }

        private OnFinishBuilder minutes(int minutes) {
            pauseableMinutesSecondsTimer.setMinutes(minutes);
            return this;
        }

        private OnFinishBuilder seconds(int seconds) {
            pauseableMinutesSecondsTimer.setSeconds(seconds);
            return this;
        }

    }

}
