package com.muckibude.cda.lightningtalktimer.ui.gestures;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

public class ChangeColorAction extends BaseViewAction {
    private final FrontPresenter frontPresenter;

    public ChangeColorAction(FrontPresenter frontPresenter) {
        super(null, null);
        this.frontPresenter = frontPresenter;
    }

    @Override
    protected void onAction(MotionEventCapture capture) {
        frontPresenter.toggleColor();
    }
}
