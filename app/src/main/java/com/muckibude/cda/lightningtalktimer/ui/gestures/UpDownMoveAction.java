package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class UpDownMoveAction extends OnDownActionCaptureViewAction {
    private static final int MAX_ALLOWED_DISTANCE = 250;
    private static final String TAG = UpDownMoveAction.class.getSimpleName();

    @Override
    protected boolean acceptActionMove(MotionEvent actualMotionEvent) {
        return onDownMotionEvent != null
                && absDistanceMoved(actualMotionEvent) > MOVE_THRESHOLD;
    }

    @Override
    protected void onActionMove(View actualView, MotionEvent actualMotionEvent) {
        super.onActionMove(actualView, actualMotionEvent);
        float distanceMoved = actualMotionEvent.getRawY() - onDownMotionEvent.getRawY();
        float sign = Math.signum(distanceMoved);
        distanceMoved = Math.abs(distanceMoved);
        float distanceAllowed = sign * Math.min(distanceMoved, MAX_ALLOWED_DISTANCE);
        actualView.animate()
                .y(distanceAllowed + onDownView.getY())
                .setDuration(0)
                .start();
        Log.d(TAG, String.format("action move event. animate(distanceAllowed(%f) + viewYPosition" +
                                         "(%f))",
                                 distanceAllowed, onDownView.getY()));
    }

}
