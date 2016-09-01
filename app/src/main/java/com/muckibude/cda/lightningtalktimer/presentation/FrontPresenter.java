package com.muckibude.cda.lightningtalktimer.presentation;

import android.util.Log;

import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.FrontModel;

import java.util.Locale;

import javax.inject.Inject;

public class FrontPresenter implements Presenter<FrontView> {
    private static final String TAG = "FrontPresenter";
    private final FrontModel frontModel;
    private final BackModel backModel;
    private FrontView frontView;

    @Inject
    public FrontPresenter(FrontModel frontModel, BackModel backModel) {
        this.frontModel = frontModel;
        this.backModel = backModel;
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

    public void startCountdown() {
        Log.d(TAG, String.valueOf(frontModel.getCountdownEntity()));
        backModel.setInitialCountdown(frontModel.getCountdownEntity());
    }

    public void toggleColor() {
        frontView.switchPickerColorTo(frontModel.getNextColor(frontView.getCurrentColor()));
    }

    public void setColorForBackground(int selectedColor) {
        backModel.setBackgroundColor(selectedColor);
    }

    public void setColorFromBackground() {
        if (null == backModel.getBackgroundColor()) {
            toggleColor();
        } else {
            frontView.switchPickerColorTo(backModel.getBackgroundColor());
        }
    }
}
