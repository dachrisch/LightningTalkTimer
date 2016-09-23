package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

public class RebaseViewAction extends OnDownActionCaptureViewAction {

    @Override
    protected boolean acceptActionUp(MotionEvent actualMotionEvent) {
        return onDownMotionEvent != null
                && absDistanceMoved(actualMotionEvent) > MOVE_THRESHOLD;
    }

    @Override
    protected void onActionUp(View view, MotionEvent motionEvent) {
        super.onActionUp(view, motionEvent);
        onDownView.getViewReference().animate().y(onDownView.getY()).setDuration(300)
                .setInterpolator(new OvershootInterpolator(5.0f)).start();
    }
}
