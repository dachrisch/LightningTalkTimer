package com.muckibude.cda.lightningtalktimer.presentation;

import android.util.Log;

import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.PauseableMinutesSecondsTimer;

import javax.inject.Inject;

public class BackPresenter implements Presenter<BackView>, PauseableMinutesSecondsTimer.OnSecondCallback, PauseableMinutesSecondsTimer.OnFinishCallback {
    private static final String TAG = "BackPresenter";
    private final BackModel backModel;
    private BackView backView;

    private final PauseableMinutesSecondsTimer pauseableMinutesSecondsTimer;

    @Inject
    public BackPresenter(final BackModel backModel, final PauseableMinutesSecondsTimer pauseableMinutesSecondsTimer) {
        this.backModel = backModel;
        this.pauseableMinutesSecondsTimer = pauseableMinutesSecondsTimer;
        this.pauseableMinutesSecondsTimer.setOnSecondsCallback(this);
        this.pauseableMinutesSecondsTimer.setOnFinishCallback(this);
    }

    @Override
    public void onSecond(long millisRemaining) {
        Log.d(TAG, "decrement one second");
        int seconds = (int) Math.ceil((Double.valueOf(millisRemaining) / 1000.0) % 60.0);
        int minutes = (int) Math.floor((Double.valueOf(millisRemaining) / 1000.0) / 60.0);
        backModel.updateCountdown(minutes, seconds);
        displayCountdown();
    }

    private void displayCountdown() {
        if (backModel.getMinutes() > 0) {
            backView.display(backModel.getMinutes(), backModel.getSeconds());
        } else {
            backView.display(backModel.getSeconds());
        }
    }

    @Override
    public void onFinish() {
        Log.d(TAG, "finished");
        onSecond(0);
        backView.blinkScreen();
    }

    @Override
    public void setView(BackView view) {
        backView = view;
    }

    public void startTimer() {
        Log.d(TAG, "start timer with: " + backModel.getCountdownEntity());
        pauseableMinutesSecondsTimer.setStartMinutes(backModel.getMinutes());
        pauseableMinutesSecondsTimer.setStartSeconds(backModel.getSeconds());
        pauseableMinutesSecondsTimer.start();
    }

    public void toggleTimer() {
        if (null != pauseableMinutesSecondsTimer) {
            if (pauseableMinutesSecondsTimer.isRunning()) {
                pauseableMinutesSecondsTimer.pause();
                pauseableMinutesSecondsTimer.setStartSeconds(backModel.getSeconds());
                pauseableMinutesSecondsTimer.setStartMinutes(backModel.getMinutes());
                backView.pause();
            } else {
                pauseableMinutesSecondsTimer.resume();
                backView.resume();
            }
        }
    }

    public void stopTimer() {
        pauseableMinutesSecondsTimer.cancel();
    }

    public void applyBackgroundColor() {
        backView.setBackgroundColor(backModel.getBackgroundColor());
    }
}
