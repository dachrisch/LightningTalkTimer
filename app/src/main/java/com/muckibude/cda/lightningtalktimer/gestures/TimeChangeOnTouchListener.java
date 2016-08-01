package com.muckibude.cda.lightningtalktimer.gestures;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.muckibude.cda.lightningtalktimer.countdown.CountdownTimeHolder;

/**
 * TimeChangeOnTouchListener
 * listens for touch movements and changes the countdown timer up or down by 15 seconds with every movement
 * Created by cda on 27.07.16.
 */
public class TimeChangeOnTouchListener implements View.OnTouchListener {

    public static final int MAX_ALLOWED_DISTANCE = 200;
    private final CountdownTimeHolder countdownTimeHolder;
    private float viewPosition;
    private float firstDownPosition;
    private static final String TAG = "TimeChangeOnTouchListen";

    public TimeChangeOnTouchListener(CountdownTimeHolder countdownTimeHolder) {
        this.countdownTimeHolder = countdownTimeHolder;
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
                animateView(view, motionEvent);
                break;
            case MotionEvent.ACTION_UP:
                boolean up = Math.signum(motionEvent.getRawY() - firstDownPosition) < 0;
                if (up) {
                    countdownTimeHolder.inc(15);
                } else {
                    countdownTimeHolder.dec(15);
                }
                Log.d(TAG, String.format("resetting to original position(%f)", viewPosition));
                view.animate().y(viewPosition).setDuration(5).start();
                break;
            default:
                return false;
        }
        return true;
    }

    private void animateView(android.view.View view, android.view.MotionEvent motionEvent) {
        float distanceMoved = motionEvent.getRawY() - firstDownPosition;
        float sign = Math.signum(distanceMoved);
        distanceMoved = Math.abs(distanceMoved);
        float distanceAllowed = sign * (MAX_ALLOWED_DISTANCE * distanceMoved / (distanceMoved + MAX_ALLOWED_DISTANCE));
        view.animate()
                .y(distanceAllowed + viewPosition)
                .setDuration(0)
                .start();
        android.util.Log.d(TAG, String.format("action move event. animate(distanceAllowed(%f) + viewPosition(%f))",
                                              distanceAllowed, viewPosition));
    }
}
