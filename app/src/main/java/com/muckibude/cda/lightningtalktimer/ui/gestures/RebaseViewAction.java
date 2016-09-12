package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

public class RebaseViewAction implements ViewAction {
    private ViewCapture onDownView;
    private MotionEventCapture onDownMotionEvent;
    private static final Double MOVE_THRESHOLD = 5.0;

    @Override
    public boolean accept(MotionEvent actualMotionEvent) {
        switch (actualMotionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
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
                onDownView = ViewCapture.captureView(actualView);
                onDownMotionEvent = MotionEventCapture.captureMotionEvent(actualMotionEvent);
                break;
            case MotionEvent.ACTION_UP:
                onDownView.getViewReference().animate().y(onDownView.getY()).setDuration(5).start();
                break;
        }
    }
}
