package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

public class ToggleTimerAction extends OnDownActionCaptureViewAction {
    private final FrontPresenter frontPresenter;

    public ToggleTimerAction(FrontPresenter frontPresenter) {
        this.frontPresenter = frontPresenter;
    }

    @Override
    protected boolean acceptActionUp(MotionEvent actualMotionEvent) {
        return onDownMotionEvent != null
                && absDistanceMoved(actualMotionEvent) > MOVE_THRESHOLD;
    }

    @Override
    protected void onActionUp(View view, MotionEvent actualMotionEvent) {
        super.onActionUp(view, actualMotionEvent);
        if (Math.signum(actualMotionEvent.getRawY() - onDownMotionEvent.getRawY()) < 0) {
            frontPresenter.increase15Seconds();
        } else {
            frontPresenter.decrease15Seconds();
        }

    }
}
