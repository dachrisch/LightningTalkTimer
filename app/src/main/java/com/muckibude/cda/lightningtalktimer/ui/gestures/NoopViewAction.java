package com.muckibude.cda.lightningtalktimer.ui.gestures;

public class NoopViewAction extends BaseViewAction {

    public NoopViewAction() {
        super(null, null);
    }

    @Override
    protected void onAction(MotionEventCapture capture) {
        // noop
    }
}
