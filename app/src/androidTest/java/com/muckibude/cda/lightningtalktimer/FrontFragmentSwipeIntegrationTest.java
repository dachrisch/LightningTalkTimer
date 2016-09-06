package com.muckibude.cda.lightningtalktimer;


import android.support.test.espresso.IdlingPolicies;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.muckibude.cda.lightningtalktimer.matcher.BackgroundColorMatcher.withBackgroundColor;
import static com.muckibude.cda.lightningtalktimer.matcher.TextViewMatcher.withTextOnView;

@RunWith(AndroidJUnit4.class)
public class FrontFragmentSwipeIntegrationTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    @Rule
    public final TestAppComponentRule componentRule = new TestAppComponentRule();

    @Before
    public void setIdleTimes() {
        IdlingPolicies.setMasterPolicyTimeout(10, TimeUnit.MINUTES);
        IdlingPolicies.setIdlingResourceTimeout(10, TimeUnit.MINUTES);
    }

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
    public void swipeDown4TimesWillChangeTimeBy1Minute() {
        onView(withId(R.id.minutes)).check(matches(withTextOnView("2")));
        onView(withId(R.id.seconds)).check(matches(withTextOnView("15")));

        onView(withId(R.id.countdownPicker)).perform(swipeDown()).perform(swipeDown()).perform(swipeDown()).perform(swipeDown());

        onView(withId(R.id.minutes)).check(matches(withTextOnView("1")));
        onView(withId(R.id.seconds)).check(matches(withTextOnView("15")));
    }

    @Test
    public void onSimpleClickColorWillChange() {
        onView(withId(R.id.countdownPicker)).check(matches(withBackgroundColor("#77ff77")));
        onView(withId(R.id.countdownPicker)).perform(click()).check(matches(withBackgroundColor("#77ddff")));
    }

}