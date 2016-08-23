package com.muckibude.cda.lightningtalktimer.matcher;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Locale;

public class TextViewMatcher {
    public static Matcher<? super View> withTextOnView(final String expected) {
        return new TypeSafeMatcher<View>() {

            private CharSequence actual;

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView)) {
                    return false;
                }
                TextView textView = (TextView) view;
                actual = textView.getText();
                return actual.equals(expected);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(String.format(Locale.getDefault(), "[%s] <> Got: [%s]", expected, actual));
            }
        };
    }
}
