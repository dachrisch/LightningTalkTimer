package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;

public abstract class BaseViewAction implements ViewAction {

    protected final ViewCapture onDownView;
    protected final MotionEventCapture onDownMotionEvent;
    private ViewAction nextViewAction;

    public BaseViewAction(ViewCapture onDownView, MotionEventCapture onDownMotionEvent) {
        this.onDownView = onDownView;
        this.onDownMotionEvent = onDownMotionEvent;
    }

    @Override
    public void onAction(MotionEvent motionEvent) {
        onAction(MotionEventCapture.captureMotionEvent(motionEvent));
        if (null != nextViewAction) {
            nextViewAction.onAction(motionEvent);
        }
    }

    protected abstract void onAction(MotionEventCapture motionEventCapture);

    public void and(ViewAction viewAction) {
        this.nextViewAction = viewAction;
    }
}
