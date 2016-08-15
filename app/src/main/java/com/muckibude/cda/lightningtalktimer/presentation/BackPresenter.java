package com.muckibude.cda.lightningtalktimer.presentation;

import android.util.Log;

import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.PauseableMinutesSecondsTimer;

import javax.inject.Inject;

public class BackPresenter implements Presenter<BackView> {
    private static final String TAG = "BackPresenter";
    private BackView backView;
    private final BackModel backModel;
    private PauseableMinutesSecondsTimer pauseableMinutesSecondsTimer;

    @Inject
    public BackPresenter(BackModel backModel) {
        this.backModel = backModel;
    }

    @Override
    public void setView(BackView view) {
        backView = view;
    }

    public void startTimer() {
        Log.d(TAG, "start timer");
        pauseableMinutesSecondsTimer = PauseableMinutesSecondsTimer.doEverySecond(new PauseableMinutesSecondsTimer.OnSecondCallback() {
            @Override
            public void onSecond() {
                Log.d(TAG, "decrement");
                backModel.decrementOneSecond();
                backView.display(backModel.getMinutes(), backModel.getSeconds());
            }
        }).startAt(backModel.getMinutes(), backModel.getSeconds()).doOnFinish(new PauseableMinutesSecondsTimer.OnFinishCallback() {
            @Override
            public void onFinish() {

            }
        }).start();
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
