package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

public interface ViewAction {
    boolean accept(MotionEvent motionEvent);

    void onAction(View view, MotionEvent motionEvent);
}
