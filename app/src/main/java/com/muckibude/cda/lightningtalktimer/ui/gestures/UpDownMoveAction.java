package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.util.Log;

public class UpDownMoveAction extends BaseViewAction {
    private static final int MAX_ALLOWED_DISTANCE = 250;

    private static final String TAG = UpDownMoveAction.class.getSimpleName();

    public UpDownMoveAction(ViewCapture onDownView, MotionEventCapture onDownMotionEvent) {
        super(onDownView, onDownMotionEvent);
    }

    @Override
    public void onAction(MotionEventCapture actualMotionEvent) {
        float distanceMoved = actualMotionEvent.getRawY() - onDownMotionEvent.getRawY();
        float sign = Math.signum(distanceMoved);
        distanceMoved = Math.abs(distanceMoved);
        float distanceAllowed = sign * Math.min(distanceMoved, MAX_ALLOWED_DISTANCE);
        onDownView.getViewReference().animate()
                .y(distanceAllowed + onDownView.getY())
                .setDuration(0)
                .start();
        Log.d(TAG, String.format("action move event. animate(distanceAllowed(%f) + viewYPosition(%f))",
                                 distanceAllowed, onDownView.getY()));
    }
}
