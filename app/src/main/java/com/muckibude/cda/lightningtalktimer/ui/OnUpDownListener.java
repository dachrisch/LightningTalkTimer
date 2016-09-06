package com.muckibude.cda.lightningtalktimer.ui;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

/**
 * OnUpDownListener
 * listens for touch movements and changes the countdown timer up or down by 15 seconds with every movement
 * Created by cda on 27.07.16.
 */
public class OnUpDownListener implements View.OnTouchListener {
    public static final int MOVE_THRESHOLD = 20;
    private final FrontPresenter frontPresenter;

    private static final int MAX_ALLOWED_DISTANCE = 250;
    private float viewPosition;
    private float firstDownPosition;
    private static final String TAG = "TimeChangeOnTouchListen";

    public OnUpDownListener(FrontPresenter frontPresenter) {
        this.frontPresenter = frontPresenter;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                firstDownPosition = motionEvent.getRawY();
                viewPosition = view.getY();
                Log.d(TAG, String.format("action down event. viewPosition = %f", viewPosition));
                break;
            case MotionEvent.ACTION_MOVE:
                if (shouldAnimate(motionEvent)) {
                    animateView(view, motionEvent);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (shouldAnimate(motionEvent)) {
                    toggleTimer(motionEvent);
                    rebaseView(view);
                } else {
                    changeColor();
                }
                break;
            default:
                return false;
        }
        return true;
    }

    private void rebaseView(View view) {
        Log.d(TAG, String.format("resetting to original position(%f)", viewPosition));
        view.animate().y(viewPosition).setDuration(5).start();
    }

    private boolean shouldAnimate(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getRawY() - firstDownPosition) > MOVE_THRESHOLD;
    }

    private void changeColor() {
        frontPresenter.toggleColor();
    }

    private void toggleTimer(MotionEvent motionEvent) {
        boolean up = Math.signum(motionEvent.getRawY() - firstDownPosition) < 0;
        if (up) {
            frontPresenter.increase15Seconds();
        } else {
            frontPresenter.decrease15Seconds();
        }

    }

    private void animateView(View view, MotionEvent motionEvent) {
        float distanceMoved = motionEvent.getRawY() - firstDownPosition;
        float sign = Math.signum(distanceMoved);
        distanceMoved = Math.abs(distanceMoved);
        float distanceAllowed = sign * (MAX_ALLOWED_DISTANCE * distanceMoved / (distanceMoved + MAX_ALLOWED_DISTANCE));
        view.animate()
                .y(distanceAllowed + viewPosition)
                .setDuration(0)
                .start();
        Log.d(TAG, String.format("action move event. animate(distanceAllowed(%f) + viewPosition(%f))",
                                 distanceAllowed, viewPosition));
    }
}
