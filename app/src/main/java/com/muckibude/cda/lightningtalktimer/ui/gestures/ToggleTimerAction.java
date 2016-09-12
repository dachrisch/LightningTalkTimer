package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

public class ToggleTimerAction implements ViewAction {
    private final FrontPresenter frontPresenter;
    private MotionEventCapture onDownMotionEvent;
    private static final Double MOVE_THRESHOLD = 5.0;

    public ToggleTimerAction(FrontPresenter frontPresenter) {

        this.frontPresenter = frontPresenter;
    }

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
    public void onAction(View view, MotionEvent actualMotionEvent) {
        switch (actualMotionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                onDownMotionEvent = MotionEventCapture.captureMotionEvent(actualMotionEvent);
                break;
            case MotionEvent.ACTION_UP:
                if (Math.signum(actualMotionEvent.getRawY() - onDownMotionEvent.getRawY()) < 0) {
                    frontPresenter.increase15Seconds();
                } else {
                    frontPresenter.decrease15Seconds();
                }
                break;
            default:
                return;
        }
    }
}
