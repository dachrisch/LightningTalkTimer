package com.muckibude.cda.lightningtalktimer.ui.gestures;

import android.view.MotionEvent;
import android.view.View;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;

import static android.view.View.OnTouchListener;

public class OnInteractionListener implements OnTouchListener {

    private ViewActionChain viewActionChain = new ViewActionChain();
    private FrontPresenter frontPresenter;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        viewActionChain.onAction(view, motionEvent);
        return true;
    }

    public void setFrontPresenter(FrontPresenter frontPresenter) {
        this.frontPresenter = frontPresenter;
        viewActionChain.add(new UpDownMoveAction());
        viewActionChain.add(new ToggleTimerAction(frontPresenter));
        viewActionChain.add(new RebaseViewAction());
        viewActionChain.add(new ChangeColorAction(frontPresenter));
    }
}
