package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class OnHoldToggleTimerViewAction extends OnDownActionCaptureViewAction {

    private final FrontPresenter frontPresenter;
    private final TimerFactory timerFactory;
    private Timer timer;

    @Inject
    public OnHoldToggleTimerViewAction(FrontPresenter frontPresenter, TimerFactory timerFactory) {
        this.frontPresenter = frontPresenter;
        this.timerFactory = timerFactory;
        timer = timerFactory.createTimer();
    }

    @Override
    protected boolean acceptActionMove(MotionEvent motionEvent) {
        return true;
    }

    @Override
    protected boolean acceptActionUp(MotionEvent motionEvent) {
        return true;
    }

    @Override
    protected void onActionMove(final View view, final MotionEvent actualMotionEvent) {
        super.onActionMove(view, actualMotionEvent);

        timer.cancel();
        timer = timerFactory.createTimer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               view.post(new Runnable() {
                                   @Override
                                   public void run() {
                                       switch (moveDirection(actualMotionEvent)) {
                                           case UP:
                                               frontPresenter.increase15Seconds();
                                               break;
                                           case DOWN:
                                               frontPresenter.decrease15Seconds();
                                               break;
                                       }
                                   }
                               });
                           }
                       }
                , 300, 200);
    }

    @Override
    protected void onActionUp(View view, MotionEvent motionEvent) {
        super.onActionUp(view, motionEvent);
        timer.cancel();
    }
}

