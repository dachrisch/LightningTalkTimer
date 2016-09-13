package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

public class OnDownActionCaptureViewAction extends OnActionViewAction {
    protected static final Double MOVE_THRESHOLD = 5.0;

    protected ViewCapture onDownView;
    protected MotionEventCapture onDownMotionEvent;

    @Override
    protected boolean acceptActionDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    protected void onActionDown(View actualView, MotionEvent actualMotionEvent) {
        super.onActionDown(actualView, actualMotionEvent);
        onDownView = ViewCapture.captureView(actualView);
        onDownMotionEvent = MotionEventCapture.captureMotionEvent(actualMotionEvent);
    }

    protected float absDistanceMoved(MotionEvent actualMotionEvent) {
        return Math.abs(actualMotionEvent.getRawY() - onDownMotionEvent.getRawY());
    }

}
