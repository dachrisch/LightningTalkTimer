package com.muckibude.cda.lightningtalktimer.ui.gestures;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

public class ToggleTimerAction extends BaseViewAction {
    private static final Double MOVE_THRESHOLD = 5.0;
    private FrontPresenter frontPresenter;

    public ToggleTimerAction(ViewCapture onDownView, MotionEventCapture onDownMotionEvent) {
        super(onDownView, onDownMotionEvent);
    }

    @Override
    public void onAction(MotionEventCapture actualMotionEvent) {
        if (Math.signum(actualMotionEvent.getRawY() - onDownMotionEvent.getRawY()) < 0) {
            frontPresenter.increase15Seconds();
        } else {
            frontPresenter.decrease15Seconds();
        }
    }

    public void setFrontPresenter(FrontPresenter frontPresenter) {
        this.frontPresenter = frontPresenter;
    }
}
