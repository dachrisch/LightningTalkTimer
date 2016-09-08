package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;

public class MotionEventCapture {

    private final float rawX;

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
    }

    public static MotionEventCapture captureMotionEvent(MotionEvent motionEvent) {
        return new MotionEventCapture(motionEvent);
    }
}
