package com.muckibude.cda.lightningtalktimer.presentation;

import com.muckibude.cda.lightningtalktimer.domain.FrontModel;

import javax.inject.Inject;

public class FrontPresenter implements Presenter<FrontView> {
    private final FrontModel frontModel;
    private FrontView frontView;

    @Inject
    public FrontPresenter(FrontModel frontModel) {
        this.frontModel = frontModel;
    }

    public void decrease15Seconds() {
        frontModel.decrease15Seconds();
        updateDisplay();
    }

    public void increase15Seconds() {
        frontModel.increase15Seconds();
        updateDisplay();
    }

    private void updateDisplay() {
        frontView.display(frontModel.getMinutesSecondsEntity().getMinutes(),
                          frontModel.getMinutesSecondsEntity().getSeconds());
    }

    @Override
    public void setView(FrontView view) {
        this.frontView = view;
        updateDisplay();
    }
}
