package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

public class ChangeColorAction extends OnDownActionCaptureViewAction {
    private final FrontPresenter frontPresenter;

    public ChangeColorAction(FrontPresenter frontPresenter) {
        this.frontPresenter = frontPresenter;
    }

    @Override
    protected boolean acceptActionUp(MotionEvent actualMotionEvent) {
        return onDownMotionEvent != null
                && absDistanceMoved(actualMotionEvent) < MOVE_THRESHOLD;
    }

    @Override
    protected void onActionUp(View view, MotionEvent motionEvent) {
        super.onActionUp(view, motionEvent);
        frontPresenter.toggleColor();
    }
}
