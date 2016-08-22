package com.muckibude.cda.lightningtalktimer;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.muckibude.cda.lightningtalktimer.matcher.BackgroundColorMatcher.withBackgroundColor;
import static com.muckibude.cda.lightningtalktimer.matcher.TextViewMatcher.withTextOnView;

@RunWith(AndroidJUnit4.class)
public class FrontCountdownTimerSettingsTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void swipeUpWillChangeTimeBy15Seconds() {
        onView(withId(R.id.seconds)).check(matches(withTextOnView("15")));
        onView(withId(R.id.countdownPicker)).perform(swipeUp());
        onView(withId(R.id.seconds)).check(matches(withTextOnView("30")));
    }

    @Test
    public void swipeDownWillChangeTimeBy15Seconds() {
        onView(withId(R.id.seconds)).check(matches(withTextOnView("15")));
        onView(withId(R.id.countdownPicker)).perform(swipeDown());
        onView(withId(R.id.seconds)).check(matches(withTextOnView("00")));
    }

    @Test
    public void swipeUp4TimesWillChangeTimeBy1Minute() {
        onView(withId(R.id.minutes)).check(matches(withTextOnView("2")));
        onView(withId(R.id.seconds)).check(matches(withTextOnView("15")));

        onView(withId(R.id.countdownPicker)).perform(swipeUp()).perform(swipeUp()).perform(swipeUp()).perform(swipeUp());

        onView(withId(R.id.minutes)).check(matches(withTextOnView("3")));
        onView(withId(R.id.seconds)).check(matches(withTextOnView("15")));
    }

    @Test
    public void onSimpleClickColorWillChange() {
        onView(withId(R.id.countdownPicker)).perform(click()).check(matches(withBackgroundColor("#ff00ff")));
    }

}