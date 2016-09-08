package com.muckibude.cda.lightningtalktimer.ui.gestures;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

public class ToggleTimerAction extends BaseViewAction {

    private FrontPresenter frontPresenter;

    public ToggleTimerAction(ViewCapture onDownView, MotionEventCapture onDownMotionEvent, FrontPresenter frontPresenter) {
        super(onDownView, onDownMotionEvent);
        this.frontPresenter = frontPresenter;
    }

    @Override
    public void onAction(MotionEventCapture actualMotionEvent) {
        if (Math.signum(actualMotionEvent.getRawY() - onDownMotionEvent.getRawY()) < 0) {
            frontPresenter.increase15Seconds();
        } else {
            frontPresenter.decrease15Seconds();
        }
    }

}
