package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;

public class ViewActionChain {

    private final Collection<ViewAction> chain = new ArrayList<>();

    public void add(ViewAction viewAction) {
        chain.add(viewAction);
    }

    public void onAction(View view, MotionEvent motionEvent) {
        for (ViewAction viewAction : chain) {
            if (viewAction.accept(motionEvent)) {
                viewAction.onAction(view, motionEvent);
            }
        }
    }
}
