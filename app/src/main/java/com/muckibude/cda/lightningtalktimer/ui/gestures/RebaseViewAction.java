package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

public class RebaseViewAction extends OnDownActionCaptureViewAction {

    @Override
    protected boolean acceptActionUp(MotionEvent actualMotionEvent) {
        return onDownMotionEvent != null
                && absDistanceMoved(actualMotionEvent) > MOVE_THRESHOLD;
    }

    @Override
    protected void onActionUp(View view, MotionEvent motionEvent) {
        super.onActionUp(view, motionEvent);
        onDownView.getViewReference().animate().y(onDownView.getY()).setDuration(5).start();
    }
}
