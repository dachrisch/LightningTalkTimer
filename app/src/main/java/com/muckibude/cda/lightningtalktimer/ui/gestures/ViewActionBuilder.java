package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

import java.util.Locale;

import static com.muckibude.cda.lightningtalktimer.ui.gestures.MotionEventCapture.captureMotionEvent;

public class ViewActionBuilder {
    private static final String TAG = ViewActionBuilder.class.getSimpleName();
    public static final NoopViewAction NOOP_VIEW_ACTION = new NoopViewAction();
    private ViewCapture onDownView = null;
    private MotionEventCapture onDownMotionEvent = null;
    private BaseViewAction viewAction = NOOP_VIEW_ACTION;
    private FrontPresenter frontPresenter;
    private static final Double MOVE_THRESHOLD = 5.0;

    public ViewActionBuilder withActionMove(MotionEvent actualMotionEvent) {
        Log.d(TAG, String.format(Locale.getDefault(), "withActionMove = {actualMotionEvent: %f, %f}",
                                 actualMotionEvent.getRawX(), actualMotionEvent.getRawY()));
        if (Math.abs(motionEventMovedDelta(actualMotionEvent)) > MOVE_THRESHOLD) {
            viewAction = new UpDownMoveAction(onDownView, onDownMotionEvent);
        }
        return this;
    }

    public ViewActionBuilder withActionDown(View view, MotionEvent motionEvent) {
        Log.d(TAG, String.format(Locale.getDefault(), "withActionDown = {view: %f, %f}, {motionEvent: %f, %f}",
                                 view.getX(), view.getY(), motionEvent.getRawX(), motionEvent.getRawY()));
        onDownMotionEvent = captureMotionEvent(motionEvent);
        onDownView = ViewCapture.captureView(view);
        viewAction = NOOP_VIEW_ACTION;
        return this;
    }

    public void withActionUp(MotionEvent actualMotionEvent) {
        Log.d(TAG, String.format(Locale.getDefault(), "withActionUp = {actualMotionEvent: %f, %f}",
                                 actualMotionEvent.getRawX(), actualMotionEvent.getRawY()));
        if (Math.abs(motionEventMovedDelta(actualMotionEvent)) > MOVE_THRESHOLD) {
            viewAction = new ToggleTimerAction(onDownView, onDownMotionEvent, frontPresenter);
            viewAction.and(new RebaseViewAction(onDownView));
        } else {
            viewAction = new ChangeColorAction(frontPresenter);
        }
    }

    private float motionEventMovedDelta(MotionEvent actualMotionEvent) {
        return actualMotionEvent.getRawY() - onDownMotionEvent.getRawY();
    }

    public ViewAction build() {
        return viewAction;
    }

    public void setFrontPresenter(FrontPresenter frontPresenter) {
        this.frontPresenter = frontPresenter;
    }
}
