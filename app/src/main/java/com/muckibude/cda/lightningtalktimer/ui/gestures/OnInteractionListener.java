package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

import javax.inject.Inject;

import static android.view.View.OnTouchListener;

public class OnInteractionListener implements OnTouchListener {

    private final ViewActionChain viewActionChain;

    @Inject
    public OnInteractionListener(ViewActionChain viewActionChain) {
        this.viewActionChain = viewActionChain;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        viewActionChain.onAction(view, motionEvent);
        return true;
    }
}
