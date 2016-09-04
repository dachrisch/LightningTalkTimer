package com.muckibude.cda.lightningtalktimer.presentation;

import android.util.Log;

import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.PausableCountdownTimer;

import javax.inject.Inject;

public class BackPresenter implements Presenter<BackView> {
    private static final String TAG = "BackPresenter";
    private final BackModel backModel;
    private BackView backView;

    private final PausableCountdownTimer pausableCountdownTimer;

    @Inject
    public BackPresenter(final BackModel backModel, final PausableCountdownTimer pausableCountdownTimer) {
        this.backModel = backModel;
        this.pausableCountdownTimer = pausableCountdownTimer;
    }

    private void displayCountdown() {
        if (backModel.getMinutes() > 0) {
            backView.display(backModel.getMinutes(), backModel.getSeconds());
        } else {
            backView.display(backModel.getSeconds());
        }
    }

    @Override
    public void setView(BackView view) {
        backView = view;
    }

    public void startTimer() {
        Log.d(TAG, "start timer with: " + backModel.getCountdownEntity());
        pausableCountdownTimer.start();
    }

    public void toggleTimer() {
        if (null != pausableCountdownTimer) {
            if (pausableCountdownTimer.isRunning()) {
                pausableCountdownTimer.pause();
                backView.pause();
            } else {
                pausableCountdownTimer.resume();
                backView.resume();
            }
        }
    }

    public void stopTimer() {
        pausableCountdownTimer.pause();
    }

    public void applyBackgroundColor() {
        backView.setBackgroundColor(backModel.getBackgroundColor());
    }
}
