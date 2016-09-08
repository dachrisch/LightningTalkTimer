package com.muckibude.cda.lightningtalktimer.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ViewAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ViewActionBuilder;

import org.junit.Test;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewActionBuilderTest {

    @Test
    public void onActionDownNothingHappens() {
        ViewActionBuilder viewActionBuilder = new ViewActionBuilder();
        MotionEvent motionEvent = mock(MotionEvent.class);
        View view = mock(View.class);
        ViewAction viewAction = viewActionBuilder.withActionDown(view, motionEvent).build();
        viewAction.onAction(motionEvent);
    }

    @Test
    public void onActionMoveUpViewWillBeAnimatedUp() {
        ViewActionBuilder viewActionBuilder = new ViewActionBuilder();
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(90));
        viewActionBuilder.withActionDown(initialView, initialMotionEvent);
        MotionEvent actualMotionEvent = mock(MotionEvent.class);
        when(actualMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        ViewAction viewAction = viewActionBuilder.withActionMove(actualMotionEvent).build();
        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        viewAction.onAction(actualMotionEvent);
        verify(initialView).animate();
        verify(viewPropertyAnimator).y(10);
    }

    @Test
    public void onActionUpTimerWillBeToggledWhenMovedUp() {
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        MotionEvent actualMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(90));
        when(actualMotionEvent.getRawY()).thenReturn(Float.valueOf(100));

        mockViewPropertyAnimator(initialView);
        FrontPresenter frontPresenter = performAction(initialMotionEvent, actualMotionEvent, initialView);
        verify(frontPresenter).decrease15Seconds();
    }

    @Test
    public void onActionUpTimerWillBeToggledWhenMovedDown() {
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        MotionEvent actualMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        when(actualMotionEvent.getRawY()).thenReturn(Float.valueOf(90));

        mockViewPropertyAnimator(initialView);
        FrontPresenter frontPresenter = performAction(initialMotionEvent, actualMotionEvent, initialView);
        verify(frontPresenter).increase15Seconds();
    }

    @Test
    public void onActionUpViewWillBeRebased() {
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        MotionEvent actualMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialView.getY()).thenReturn(Float.valueOf(133));
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        when(actualMotionEvent.getRawY()).thenReturn(Float.valueOf(90));

        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        performAction(initialMotionEvent, actualMotionEvent, initialView);
        verify(viewPropertyAnimator).y(133);
    }

    private FrontPresenter performAction(MotionEvent initialMotionEvent, MotionEvent actualMotionEvent, View initialView) {
        ViewActionBuilder viewActionBuilder = new ViewActionBuilder();
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        viewActionBuilder.setFrontPresenter(frontPresenter);
        viewActionBuilder
                .withActionDown(initialView, initialMotionEvent)
                .withActionMove(actualMotionEvent)
                .withActionUp(actualMotionEvent);
        ViewAction viewAction = viewActionBuilder.build();
        viewAction.onAction(actualMotionEvent);
        return frontPresenter;
    }

    public static ViewPropertyAnimator mockViewPropertyAnimator(View initialView) {
        ViewPropertyAnimator viewPropertyAnimator = mock(ViewPropertyAnimator.class);
        when(initialView.animate()).thenReturn(viewPropertyAnimator);
        when(viewPropertyAnimator.y(anyFloat())).thenReturn(viewPropertyAnimator);
        when(viewPropertyAnimator.setDuration(anyLong())).thenReturn(viewPropertyAnimator);
        return viewPropertyAnimator;
    }
}
