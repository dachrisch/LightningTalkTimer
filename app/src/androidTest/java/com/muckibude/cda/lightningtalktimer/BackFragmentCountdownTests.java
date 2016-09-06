package com.muckibude.cda.lightningtalktimer;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.muckibude.cda.lightningtalktimer.matcher.ImageResourceMatcher.withDrawable;
import static com.muckibude.cda.lightningtalktimer.matcher.TextViewMatcher.withTextOnView;

@RunWith(AndroidJUnit4.class)
public class BackFragmentCountdownTests {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test(expected = NoMatchingViewException.class)
    public void backFragmentBigNumberViewNotVisibleOnStart() {
        onView(withId(R.id.big_number_text_view)).check(matches(isDisplayed()));
    }

    @Test(expected = NoMatchingViewException.class)
    public void backFragmentSmallNumberViewNotVisibleOnStart() {
        onView(withId(R.id.small_number_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void onResumeTimerWillResume() {
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.pauseButton)).check(matches(withDrawable(R.drawable.pause_overlay)));
        onView(withId(R.id.pauseButton)).perform(click());
        onView(withId(R.id.pauseButton)).check(matches(withDrawable(R.drawable.play_overlay_icon)));
    }

    @Test
    public void countdownDisplayShownWhenStartButtonPressed() {
        onView(withId(R.id.minutes)).check(matches(withTextOnView("2")));
        onView(withId(R.id.seconds)).check(matches(withTextOnView("15")));
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.big_number_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.small_number_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void oneSecondPassedAfterStart() {
        onView(withId(R.id.minutes)).check(matches(withTextOnView("2")));
        onView(withId(R.id.seconds)).check(matches(withTextOnView("15")));
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.big_number_text_view)).check(matches(withTextOnView("2")));
        onView(withId(R.id.small_number_text_view)).check(matches(withTextOnView("14")));
    }


}
