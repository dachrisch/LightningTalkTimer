package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;

public class MotionEventCapture {

    private final float rawX;
    private final int action;

    public float getRawY() {
        return rawY;
    }

    public float getRawX() {
        return rawX;
    }

    private final float rawY;

    private MotionEventCapture(MotionEvent motionEvent) {
        this.rawX = motionEvent.getRawX();
        rawY = motionEvent.getRawY();
        action = motionEvent.getAction();
    }

    public static MotionEventCapture captureMotionEvent(MotionEvent motionEvent) {
        return new MotionEventCapture(motionEvent);
    }

    public int getAction() {
        return action;
    }
}
