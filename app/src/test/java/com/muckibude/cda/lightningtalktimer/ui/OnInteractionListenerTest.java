package com.muckibude.cda.lightningtalktimer.ui;

import android.animation.TimeInterpolator;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ChangeColorAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.OnHoldToggleTimerViewAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.OnInteractionListener;
import com.muckibude.cda.lightningtalktimer.ui.gestures.RebaseViewAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ToggleTimerAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.UpDownMoveAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ViewAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ViewActionChain;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashSet;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OnInteractionListenerTest {

    public static ViewPropertyAnimator mockViewPropertyAnimator(View initialView) {
        ViewPropertyAnimator viewPropertyAnimator = mock(ViewPropertyAnimator.class);
        when(initialView.animate()).thenReturn(viewPropertyAnimator);
        when(viewPropertyAnimator.y(anyFloat())).thenReturn(viewPropertyAnimator);
        when(viewPropertyAnimator.setDuration(anyLong())).thenReturn(viewPropertyAnimator);
        when(viewPropertyAnimator.setInterpolator((TimeInterpolator) anyObject())).thenReturn
                (viewPropertyAnimator);
        return viewPropertyAnimator;
    }

    @Test
    public void onUpMovementViewIsMovedUp() {
        View.OnTouchListener onInteractionListener = new OnInteractionListener(viewActionChainWith(new UpDownMoveAction()));
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        verify(viewPropertyAnimator).y(10);
    }

    @Test
    public void onDownMovementViewIsMovedDown() {
        View.OnTouchListener onInteractionListener = new OnInteractionListener(viewActionChainWith(new UpDownMoveAction()));
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        verify(viewPropertyAnimator).y(-10);
    }

    @Test
    public void onDownMovementSecondsAreDecremented() {
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        View.OnTouchListener onInteractionListener = new OnInteractionListener(viewActionChainWith(new ToggleTimerAction(frontPresenter)));
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        verify(frontPresenter).decrease15Seconds();
    }

    @NonNull
    private ViewActionChain viewActionChainWith(ViewAction viewAction) {
        HashSet<ViewAction> chain = new HashSet<>();
        chain.add(viewAction);
        return new ViewActionChain(chain);
    }

    @Test
    public void onHoldAfterUpMovementTimerWillIncrease() {
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        View.OnTouchListener onInteractionListener = new OnInteractionListener(
                viewActionChainWith(new OnHoldToggleTimerViewAction(frontPresenter, new NoDelayTimerFactory())));
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(90));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        postExecutesDirectly(initialView);
        verify(frontPresenter).increase15Seconds();
    }

    private void postExecutesDirectly(View initialView) {
        ArgumentCaptor<Runnable> runnableArgumentCaptor = ArgumentCaptor.forClass(Runnable.class);
        verify(initialView).post(runnableArgumentCaptor.capture());
        runnableArgumentCaptor.getValue().run();
    }

    @Test
    public void onHoldAfterDownMovementTimerWillDecrease() {
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        View.OnTouchListener onInteractionListener = new OnInteractionListener(
                viewActionChainWith(new OnHoldToggleTimerViewAction(frontPresenter, new NoDelayTimerFactory())));
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(90));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        postExecutesDirectly(initialView);
        verify(frontPresenter).decrease15Seconds();
    }

    @Test
    public void onUpMovementSecondsAreIncremented() {
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        View.OnTouchListener onInteractionListener = new OnInteractionListener(viewActionChainWith(new ToggleTimerAction(frontPresenter)));
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        verify(frontPresenter).increase15Seconds();
    }

    @Test
    public void onNoMovementTimerIsUnchanged() {
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        View.OnTouchListener onInteractionListener = new OnInteractionListener(viewActionChainWith(new ToggleTimerAction(frontPresenter)));
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        onInteractionListener.onTouch(initialView, initialMotionEvent);

        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        verify(frontPresenter, never()).decrease15Seconds();
        verify(frontPresenter, never()).increase15Seconds();
    }

    @Test
    public void whenSimpleClickedColorIsChanged() {
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        View.OnTouchListener onInteractionListener = new OnInteractionListener(viewActionChainWith(new ChangeColorAction(frontPresenter)));
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        onInteractionListener.onTouch(initialView, initialMotionEvent);

        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        verify(frontPresenter).toggleColor();
    }

    @Test
    public void whenSecondMovementStartsActionIsReset() {
        View.OnTouchListener onInteractionListener = new OnInteractionListener(viewActionChainWith(new RebaseViewAction()));
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        onInteractionListener.onTouch(initialView, initialMotionEvent);
        verify(viewPropertyAnimator).y(0);
    }

}
