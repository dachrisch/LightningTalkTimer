package com.muckibude.cda.lightningtalktimer.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;

import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;
import com.muckibude.cda.lightningtalktimer.ui.gestures.OnInteractionListener;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ViewActionBuilder;

import org.junit.Test;

import static com.muckibude.cda.lightningtalktimer.ui.ViewActionBuilderTest.mockViewPropertyAnimator;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class OnInteractionListenerTest {

    @Test
    public void onDownMovementSecondsAreDecremented() {
        ViewActionBuilder viewActionBuilder = new ViewActionBuilder();
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        viewActionBuilder.setFrontPresenter(frontPresenter);
        OnInteractionListener upDownListener = new OnInteractionListener(viewActionBuilder);
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        upDownListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        upDownListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        verify(viewPropertyAnimator).y(10);
        viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        upDownListener.onTouch(initialView, initialMotionEvent);
        verify(viewPropertyAnimator).y(0);
        verify(frontPresenter).decrease15Seconds();
    }

    @Test
    public void onUpMovementSecondsAreIncremented() {
        ViewActionBuilder viewActionBuilder = new ViewActionBuilder();
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        viewActionBuilder.setFrontPresenter(frontPresenter);
        OnInteractionListener upDownListener = new OnInteractionListener(viewActionBuilder);
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        upDownListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        upDownListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        verify(viewPropertyAnimator).y(-10);
        viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        upDownListener.onTouch(initialView, initialMotionEvent);
        verify(viewPropertyAnimator).y(0);
        verify(frontPresenter).increase15Seconds();
    }

    @Test
    public void onNoMovementTimerIsUnchanged() {
        ViewActionBuilder viewActionBuilder = new ViewActionBuilder();
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        viewActionBuilder.setFrontPresenter(frontPresenter);
        OnInteractionListener upDownListener = new OnInteractionListener(viewActionBuilder);
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        upDownListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        upDownListener.onTouch(initialView, initialMotionEvent);
        verifyZeroInteractions(viewPropertyAnimator);

        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        upDownListener.onTouch(initialView, initialMotionEvent);
        verifyZeroInteractions(viewPropertyAnimator);
        verify(frontPresenter, never()).decrease15Seconds();
        verify(frontPresenter, never()).increase15Seconds();
    }

    @Test
    public void whenSimpleClickedColorIsChanged() {
        ViewActionBuilder viewActionBuilder = new ViewActionBuilder();
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        viewActionBuilder.setFrontPresenter(frontPresenter);
        OnInteractionListener upDownListener = new OnInteractionListener(viewActionBuilder);
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        upDownListener.onTouch(initialView, initialMotionEvent);

        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        upDownListener.onTouch(initialView, initialMotionEvent);
        verifyZeroInteractions(viewPropertyAnimator);
        verify(frontPresenter).toggleColor();
    }

    @Test
    public void whenSecondMovementStartsActionIsReset() {
        ViewActionBuilder viewActionBuilder = new ViewActionBuilder();
        FrontPresenter frontPresenter = mock(FrontPresenter.class);
        viewActionBuilder.setFrontPresenter(frontPresenter);
        OnInteractionListener upDownListener = new OnInteractionListener(viewActionBuilder);
        MotionEvent initialMotionEvent = mock(MotionEvent.class);
        View initialView = mock(View.class);
        ViewPropertyAnimator viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        upDownListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(100));
        upDownListener.onTouch(initialView, initialMotionEvent);
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_UP);
        verify(viewPropertyAnimator).y(-10);
        viewPropertyAnimator = mockViewPropertyAnimator(initialView);
        upDownListener.onTouch(initialView, initialMotionEvent);
        verify(viewPropertyAnimator).y(0);
        verify(frontPresenter).increase15Seconds();
        when(initialMotionEvent.getAction()).thenReturn(MotionEvent.ACTION_DOWN);
        when(initialMotionEvent.getRawY()).thenReturn(Float.valueOf(110));
        verifyNoMoreInteractions(frontPresenter);
    }

}
