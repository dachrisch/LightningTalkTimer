package com.muckibude.cda.lightningtalktimer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.FrontModel;
import com.muckibude.cda.lightningtalktimer.injection.MockAppModule;
import com.muckibude.cda.lightningtalktimer.injection.TestAppComponentRule;
import com.muckibude.cda.lightningtalktimer.ui.BackCountdownDisplayFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.muckibude.cda.lightningtalktimer.matcher.TextViewMatcher.withTextOnView;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
public class BackFragmentVariableRunTest {

    @Rule
    public final TestAppComponentRule componentRule = new TestAppComponentRule();
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class, false, false);
    private boolean blinkScreenInvoked = false;
    private CountdownEntity minutesSecondsEntity;

    @Before
    @SuppressLint({"ValidFragment"})
    public void mockModel() {
        MockAppModule mockAppModule = componentRule.getMockAppModule();

        minutesSecondsEntity = new CountdownEntity(0, 1);
        mockAppModule.setOverrideFrontModel(new FrontModel(minutesSecondsEntity));
        mockAppModule.setOverrideBackView(new BackCountdownDisplayFragment() {
            @Override
            public void blinkScreen() {
                blinkScreenInvoked = true;
            }
        });

        Intent intent = new Intent(getTargetContext(), MainActivity.class);
        intent.putExtra("commitFragment", false);

        mActivityRule.launchActivity(intent);
    }


    @Test
    public void whenFinishedZeroSecondsDisplayed() throws InterruptedException {
        onView(withId(R.id.minutes)).check(matches(withTextOnView("0")));
        onView(withId(R.id.seconds)).check(matches(withTextOnView("01")));
        onView(withId(R.id.startButton)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.big_number_text_view)).check(matches(withTextOnView("0")));
        onView(withId(R.id.small_number_text_view)).check(matches(withTextOnView("00")));
    }

    @Test
    public void whenFinishedDisplayBlinks() throws InterruptedException {
        onView(withId(R.id.minutes)).check(matches(withTextOnView("0")));
        onView(withId(R.id.seconds)).check(matches(withTextOnView("01")));
        onView(withId(R.id.startButton)).perform(click());
        Thread.sleep(1500);
        assertThat("blink screen not invoked", blinkScreenInvoked, is(true));
    }

    @Test
    public void lastSecondOfAMinuteIsDisplayedAsMinuteWithZeroSeconds() throws InterruptedException {
        minutesSecondsEntity.registerEntityChangeListener(null);
        minutesSecondsEntity.updateFromMillis(61 * 1000);
        onView(withId(R.id.startButton)).perform(click());
        Thread.sleep(1100);
        onView(withId(R.id.big_number_text_view)).check(matches(withTextOnView("1")));
        onView(withId(R.id.small_number_text_view)).check(matches(withTextOnView("00")));

    }
}


