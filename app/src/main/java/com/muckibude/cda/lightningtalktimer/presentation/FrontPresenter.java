package com.muckibude.cda.lightningtalktimer.presentation;

import android.os.Bundle;
import android.util.Log;

import com.muckibude.cda.lightningtalktimer.domain.FrontModel;

import java.util.Locale;

import javax.inject.Inject;

public class FrontPresenter implements Presenter<FrontView> {
    private static final String TAG = "FrontPresenter";
    private final FrontModel frontModel;
    private FrontView frontView;

    @Inject
    public FrontPresenter(FrontModel frontModel) {
        this.frontModel = frontModel;

    }

    public void decrease15Seconds() {
        Log.d(TAG, String.format(Locale.getDefault(), "%s - 00:15", frontModel.getCountdownEntity()));
        frontModel.decrease15Seconds();
        updateDisplay();
    }

    public void increase15Seconds() {
        Log.d(TAG, String.format(Locale.getDefault(), "%s + 00:15", frontModel.getCountdownEntity()));
        frontModel.increase15Seconds();
        updateDisplay();
    }

    private void updateDisplay() {
        frontView.display(frontModel.getCountdownEntity().getMinutes(),
                          frontModel.getCountdownEntity().getSeconds());
    }

    @Override
    public void setView(FrontView view) {
        this.frontView = view;
        updateDisplay();
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
}
