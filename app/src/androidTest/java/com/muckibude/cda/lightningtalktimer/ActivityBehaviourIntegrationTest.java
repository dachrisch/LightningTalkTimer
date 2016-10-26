package com.muckibude.cda.lightningtalktimer;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoActivityResumedException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.muckibude.cda.lightningtalktimer.injection.TestAppComponentRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ActivityBehaviourIntegrationTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Rule
    public final TestAppComponentRule componentRule = new TestAppComponentRule();

    @Test
    public void givenBackViewWhenBackButtonPressedShowFrontView() {
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.small_number_text_view)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.minutes)).check(matches(isDisplayed()));
    }

    @Test(expected = NoActivityResumedException.class)
    public void givenFrontViewWhenBackButtonPressedExitApp() {
        onView(withId(R.id.minutes)).check(matches(isDisplayed()));
        Espresso.pressBack();
    }

}
