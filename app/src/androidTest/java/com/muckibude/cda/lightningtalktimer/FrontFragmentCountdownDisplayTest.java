package com.muckibude.cda.lightningtalktimer;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.FrontModel;
import com.muckibude.cda.lightningtalktimer.injection.MockAppModule;
import com.muckibude.cda.lightningtalktimer.injection.TestAppComponentRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.muckibude.cda.lightningtalktimer.matcher.TextViewMatcher.withTextOnView;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class FrontFragmentCountdownDisplayTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class, false, false);
    @Rule
    public final TestAppComponentRule componentRule = new TestAppComponentRule();

    @Before
    public void mockFrontModel() {
        MockAppModule mockAppModule = componentRule.getMockAppModule();
        mockAppModule.setOverrideFrontModel(new FrontModel(new CountdownEntity(10, 0)));
        Intent intent = new Intent(getTargetContext(), MainActivity.class);
        intent.putExtra("commitFragment", false);

        mActivityRule.launchActivity(intent);
    }

    @Test
    public void below10MinutesMinutesAndSecondsAreDisplayed() {
        onView(withId(R.id.minutes)).check(matches(withTextOnView("10")));
        onView(withId(R.id.seconds)).check(matches(not(isDisplayed())));
        onView(withId(R.id.countdownPicker)).perform(ViewActions.swipeDown());
        onView(withId(R.id.minutes)).check(matches(withTextOnView("9")));
        onView(withId(R.id.seconds)).check(matches(isDisplayed()));
        onView(withId(R.id.seconds)).check(matches(withTextOnView("45")));
    }

    @Test
    public void above10MinutesMinutesAreDisplayed() {
        onView(withId(R.id.minutes)).check(matches(withTextOnView("10")));
        onView(withId(R.id.seconds)).check(matches(not(isDisplayed())));
    }

}
