package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class UpDownMoveAction implements ViewAction {
    private static final int MAX_ALLOWED_DISTANCE = 250;

    private static final String TAG = UpDownMoveAction.class.getSimpleName();
    private MotionEventCapture onDownMotionEvent;
    private ViewCapture onDownView;
    private static final Double MOVE_THRESHOLD = 5.0;

    @Override
    public boolean accept(MotionEvent actualMotionEvent) {
        switch (actualMotionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                return onDownMotionEvent != null
                        && Math.abs(actualMotionEvent.getRawY() - onDownMotionEvent.getRawY()) > MOVE_THRESHOLD;
            default:
                return false;
        }
    }

    @Override
    public void onAction(View actualView, MotionEvent actualMotionEvent) {
        switch (actualMotionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                onDownMotionEvent = MotionEventCapture.captureMotionEvent(actualMotionEvent);
                onDownView = ViewCapture.captureView(actualView);
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceMoved = actualMotionEvent.getRawY() - onDownMotionEvent.getRawY();
                float sign = Math.signum(distanceMoved);
                distanceMoved = Math.abs(distanceMoved);
                float distanceAllowed = sign * Math.min(distanceMoved, MAX_ALLOWED_DISTANCE);
                actualView.animate()
                        .y(distanceAllowed + onDownView.getY())
                        .setDuration(0)
                        .start();
                Log.d(TAG, String.format("action move event. animate(distanceAllowed(%f) + viewYPosition(%f))",
                                         distanceAllowed, onDownView.getY()));
                break;
            default:
                return;
        }
    }
}
