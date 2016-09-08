package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.View;

public class ViewCapture {

    private final float x;
    private final float y;
    private final View viewReference;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public View getViewReference() {
        return viewReference;
    }

    private ViewCapture(View view) {
        x = view.getX();
        y = view.getY();
        viewReference = view;
    }

    public static ViewCapture captureView(View view) {
        return new ViewCapture(view);
    }
}
