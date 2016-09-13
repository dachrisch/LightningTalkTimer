package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

import java.util.Set;

import javax.inject.Inject;

public class ViewActionChain {

    private final Set<ViewAction> chain;

    @Inject
    public ViewActionChain(Set<ViewAction> chain) {
        this.chain = chain;
    }

    public void onAction(View view, MotionEvent motionEvent) {
        for (ViewAction viewAction : chain) {
            if (viewAction.accept(motionEvent)) {
                viewAction.onAction(view, motionEvent);
            }
        }
    }
}
