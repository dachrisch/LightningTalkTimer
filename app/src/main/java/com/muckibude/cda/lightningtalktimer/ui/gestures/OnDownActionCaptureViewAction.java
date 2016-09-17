package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

public class OnDownActionCaptureViewAction extends OnActionViewAction {
    protected static final Double MOVE_THRESHOLD = 5.0;

    protected ViewCapture onDownView;
    protected MotionEventCapture onDownMotionEvent;

    enum MOVE_DIRECTION {
        UP,
        NO_MOVE,
        DOWN
    }

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

    protected MOVE_DIRECTION moveDirection(MotionEvent actualMotionEvent) {
        return moveDirection(MotionEventCapture.captureMotionEvent(actualMotionEvent));
    }

    protected MOVE_DIRECTION moveDirection(MotionEventCapture actualMotionEvent) {
        return MOVE_DIRECTION.values()[(int) Math.signum(actualMotionEvent.getRawY() - onDownMotionEvent.getRawY()) + 1];
    }
}
