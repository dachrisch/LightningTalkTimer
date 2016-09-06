package com.muckibude.cda.lightningtalktimer.presentation;

import android.os.Bundle;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.data.EntityChangeListener;
import com.muckibude.cda.lightningtalktimer.domain.BackModel;

import javax.inject.Inject;

public class BackPresenter implements Presenter<BackView>, EntityChangeListener<CountdownEntity> {
    private static final String TAG = "BackPresenter";
    private final BackModel backModel;
    // private final FrontModel frontModel;
    private BackView backView;


    @Inject
    public BackPresenter(final BackModel backModel) {
        this.backModel = backModel;
        backModel.registerEntityChangeListener(this);
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
        CountdownEntity startCountdown = (CountdownEntity) arguments.getSerializable("startCountdown");
        backModel.setStartCountdown(startCountdown);
        inform(startCountdown);
    }
}
