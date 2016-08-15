package com.muckibude.cda.lightningtalktimer.presentation;

import android.util.Log;

import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.CountDownBuilder;
import com.muckibude.cda.lightningtalktimer.domain.PauseableMinutesSecondsTimer;

import javax.inject.Inject;

public class BackPresenter implements Presenter<BackView>, PauseableMinutesSecondsTimer.OnSecondCallback, PauseableMinutesSecondsTimer.OnFinishCallback {
    private static final String TAG = "BackPresenter";
    private final BackModel backModel;
    private BackView backView;
    private PauseableMinutesSecondsTimer pauseableMinutesSecondsTimer;

    private final CountDownBuilder countDownBuilder;

    @Inject
    public BackPresenter(final BackModel backModel) {
        this.backModel = backModel;
        countDownBuilder = new CountDownBuilder(this);
    }

    @Override
    public void onSecond() {
        Log.d(TAG, "decrement one second");
        backModel.decrementOneSecond();
        backView.display(backModel.getMinutes(), backModel.getSeconds());
    }

    @Override
    public void onFinish() {
        Log.d(TAG, "finished");
        backModel.decrementOneSecond();
        backView.display(backModel.getMinutes(), backModel.getSeconds());
    }
    @Override
    public void setView(BackView view) {
        backView = view;
    }

    public void startTimer() {
        Log.d(TAG, "start timer");
        pauseableMinutesSecondsTimer = countDownBuilder.start(backModel.getMinutes(), backModel.getSeconds());

    }

    public void toggleTimer() {
        if (null != pauseableMinutesSecondsTimer) {
            if (pauseableMinutesSecondsTimer.isRunning()) {
                pauseableMinutesSecondsTimer.pause();
                backView.pause();
            } else {
                pauseableMinutesSecondsTimer.resume();
                backView.resume();
            }
        }
    }
}
