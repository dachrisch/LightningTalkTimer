package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

public class OnActionViewAction implements ViewAction {
    @Override
    public void onAction(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                onActionDown(view, motionEvent);
                break;
            case MotionEvent.ACTION_UP:
                onActionUp(view, motionEvent);
                break;
            case MotionEvent.ACTION_MOVE:
                onActionMove(view, motionEvent);
                break;
        }
    }

    @Override
    public boolean accept(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                return acceptActionDown(motionEvent);
            case MotionEvent.ACTION_UP:
                return acceptActionUp(motionEvent);
            case MotionEvent.ACTION_MOVE:
                return acceptActionMove(motionEvent);
            default:
                return false;
        }
    }

    boolean acceptActionMove(MotionEvent motionEvent) {
        return false;
    }

    boolean acceptActionUp(MotionEvent motionEvent) {
        return false;
    }

    boolean acceptActionDown(MotionEvent motionEvent) {
        return false;
    }

    void onActionMove(View view, MotionEvent motionEvent) {

    }

    void onActionUp(View view, MotionEvent motionEvent) {

    }

    void onActionDown(View view, MotionEvent motionEvent) {

    }
}
