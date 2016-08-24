package com.muckibude.cda.lightningtalktimer.matcher;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class BackgroundColorMatcher extends TypeSafeMatcher<View> {

    private final int expectedColor;
    private int actualColor;

    public static Matcher<View> withBackgroundColor(final String color) {
        return new BackgroundColorMatcher(color);
    }

    private BackgroundColorMatcher(String rgbColor) {
        super(View.class);
        this.expectedColor = Color.parseColor(rgbColor);
    }

    @Override
    protected boolean matchesSafely(View target) {
        Drawable drawable = target.getBackground();
        if (!(drawable instanceof GradientDrawable)) {
            return false;
        }
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        actualColor = gradientDrawable.getColor().getDefaultColor();
        Log.d("BackgroundColorMatcher", Integer.toString(actualColor));
        return expectedColor == actualColor;
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("expected background color: ");
        description.appendValue(String.format("#%06X", 0xFFFFFF & expectedColor));
        description.appendText(" <> Got: [");
        description.appendText(String.format("#%06X", 0xFFFFFF & actualColor));
        description.appendText("]");
    }
}
