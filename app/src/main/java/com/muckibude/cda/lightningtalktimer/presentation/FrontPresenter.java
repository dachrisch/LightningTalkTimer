package com.muckibude.cda.lightningtalktimer.presentation;

import android.os.Bundle;
import android.util.Log;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.data.EntityChangeListener;
import com.muckibude.cda.lightningtalktimer.domain.FrontModel;

import java.util.Locale;

import javax.inject.Inject;

public class FrontPresenter implements Presenter<FrontView>, EntityChangeListener<CountdownEntity> {
    private static final String TAG = "FrontPresenter";
    private final FrontModel frontModel;
    private FrontView frontView;

    @Inject
    public FrontPresenter(FrontModel frontModel) {
        this.frontModel = frontModel;
        this.frontModel.registerEntityChangeListener(this);
    }

    public void decrease15Seconds() {
        if (frontModel.getCountdownEntity().getMinutes() > 10) {
            Log.d(TAG, String.format(Locale.getDefault(), "%s - 01:00", frontModel
                    .getCountdownEntity()));
            frontModel.decreaseOneMinute();
        } else {
            Log.d(TAG, String.format(Locale.getDefault(), "%s - 00:15", frontModel
                    .getCountdownEntity
                            ()));
            frontModel.decrease15Seconds();
        }
    }

    public void increase15Seconds() {
        if (frontModel.getCountdownEntity().getMinutes() > 9) {
            Log.d(TAG, String.format(Locale.getDefault(), "%s + 01:00", frontModel
                    .getCountdownEntity()));
            frontModel.increaseOneMinute();
        } else {
            Log.d(TAG, String.format(Locale.getDefault(), "%s + 00:15", frontModel
                    .getCountdownEntity()));

            frontModel.increase15Seconds();
        }
    }

    @Override
    public void setView(FrontView view) {
        this.frontView = view;
        inform(frontModel.getCountdownEntity());
    }

    public void toggleColor() {
        frontView.switchPickerColorTo(frontModel.getNextColor());
    }

    public void setInitialColor() {
        frontView.switchPickerColorTo(frontModel.getCurrentColor());
    }

    public Bundle getBackArguments() {
        Bundle arguments = new Bundle();
        arguments.putSerializable("startCountdown", frontModel.getCountdownEntity());
        arguments.putInt("backgroundColor", frontModel.getCurrentColor());
        return arguments;
    }

    @Override
    public void inform(CountdownEntity changedEntity) {
        if (changedEntity.getMinutes() >= 10) {
            frontView.display(changedEntity.getMinutes());
        } else {
            frontView.display(changedEntity.getMinutes(), changedEntity.getSeconds());
        }
    }
}

