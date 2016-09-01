package com.muckibude.cda.lightningtalktimer;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.muckibude.cda.lightningtalktimer.matcher.BackgroundColorMatcher.withBackgroundColor;

@RunWith(AndroidJUnit4.class)
public class BackFragmentColorIntegrationTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void backgroundColorTakenFromFrontFragment() {
        onView(withId(R.id.countdownPicker)).check(matches(withBackgroundColor("#77ff77")));
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.back_fragment_view)).check(matches(withBackgroundColor("#77ff77")));
    }

    @Test
    public void backgroundColorStaysSameWhenGoingBack() {
        onView(withId(R.id.countdownPicker)).check(matches(withBackgroundColor("#77ff77")));
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.back_fragment_view)).check(matches(withBackgroundColor("#77ff77")));
        mActivityRule.getActivity().getFragmentManager().popBackStack();
        onView(withId(R.id.countdownPicker)).check(matches(withBackgroundColor("#77ff77")));
    }
}
