package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.util.Log;

public class RebaseViewAction extends BaseViewAction {
    private static final String TAG = RebaseViewAction.class.getSimpleName();

    public RebaseViewAction(ViewCapture onDownView) {
        super(onDownView, null);
    }

    @Override
    protected void onAction(MotionEventCapture capture) {
        Log.d(TAG, String.format("resetting to original position(%f)", onDownView.getY()));
        onDownView.getViewReference().animate().y(onDownView.getY()).setDuration(5).start();

    }
}
