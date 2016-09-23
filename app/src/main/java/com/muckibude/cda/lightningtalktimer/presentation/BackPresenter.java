package com.muckibude.cda.lightningtalktimer.presentation;

import android.os.Bundle;
import android.util.Log;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.data.EntityChangeListener;
import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.timer.OnFinishedListener;

import javax.inject.Inject;

public class BackPresenter implements Presenter<BackView>, EntityChangeListener<CountdownEntity>,
        OnFinishedListener {
    private static final String TAG = "BackPresenter";
    private final BackModel backModel;
    private BackView backView;


    @Inject
    public BackPresenter(final BackModel backModel) {
        this.backModel = backModel;
        this.backModel.registerEntityChangeListener(this);
        this.backModel.registerOnFinishedListener(this);
    }

    @Override
    public void setView(BackView view) {
        backView = view;
    }

    public void startTimer() {
        backModel.startCountdown();
    }

    public void toggleTimer() {
        if (backModel.toggleTimer()) {
            backView.resume();
        } else {
            backView.pause();
        }
    }

    public void stopTimer() {
        backModel.stopTimer();
    }

    public void applyBackgroundColor() {
        backView.setBackgroundColor(backModel.getBackgroundColor());
    }

    @Override
    public void inform(CountdownEntity changedEntity) {
        if (changedEntity.getMinutes() > 0) {
            backView.display(changedEntity.getMinutes(), changedEntity.getSeconds());
        } else {
            backView.display(changedEntity.getSeconds());
        }
    }


    public void getFrontArguments(Bundle arguments) {
        backModel.setBackgroundColor(arguments.getInt("backgroundColor"));
        CountdownEntity startCountdown = (CountdownEntity) arguments.getSerializable
                ("startCountdown");
        backModel.setStartCountdown(startCountdown);
        inform(startCountdown);
    }

    @Override
    public void informFinished() {
        Log.d(TAG, "informFinished");
        backView.blinkScreen();
    }
}
