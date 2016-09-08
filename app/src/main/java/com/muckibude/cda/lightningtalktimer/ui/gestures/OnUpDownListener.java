package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

import javax.inject.Inject;

import static android.view.View.OnTouchListener;

public class OnUpDownListener implements OnTouchListener {

    private ViewActionBuilder viewActionBuilder;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                viewActionBuilder.withActionDown(view, motionEvent);
                break;
            case MotionEvent.ACTION_MOVE:
                viewActionBuilder.withActionMove(motionEvent);
                break;
            case MotionEvent.ACTION_UP:
                viewActionBuilder.withActionUp(motionEvent);
                break;
            default:
                return false;
        }
        viewActionBuilder.build().onAction(motionEvent);
        return true;
    }

    @Inject
    public OnUpDownListener(ViewActionBuilder viewActionBuilder) {
        this.viewActionBuilder = viewActionBuilder;
    }

    public void setFrontPresenter(FrontPresenter frontPresenter) {
        this.viewActionBuilder.setFrontPresenter(frontPresenter);
    }
}
