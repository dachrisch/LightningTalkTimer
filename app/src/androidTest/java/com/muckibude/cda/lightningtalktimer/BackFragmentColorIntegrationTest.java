package com.muckibude.cda.lightningtalktimer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.ColorProvider;
import com.muckibude.cda.lightningtalktimer.domain.FrontModel;
import com.muckibude.cda.lightningtalktimer.injection.MockAppModule;
import com.muckibude.cda.lightningtalktimer.injection.TestAppComponentRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.muckibude.cda.lightningtalktimer.matcher.BackgroundColorMatcher
        .withBackgroundColor;

@RunWith(AndroidJUnit4.class)
public class BackFragmentColorIntegrationTest {
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class, false, false);
    @Rule
    public final TestAppComponentRule componentRule = new TestAppComponentRule();
    private FrontModel overrideFrontModel;

    @Before
    @SuppressLint({"ValidFragment"})
    public void mockModel() {
        MockAppModule mockAppModule = componentRule.getMockAppModule();

        overrideFrontModel = new FrontModel(new CountdownEntity(1, 1));
        overrideFrontModel.setColors(new ColorProvider());
        mockAppModule.setOverrideFrontModel(overrideFrontModel);


        Intent intent = new Intent(getTargetContext(), MainActivity.class);
        intent.putExtra("commitFragment", false);

        mActivityRule.launchActivity(intent);
    }

    @Test
    public void backgroundColorTakenFromFrontFragment() {
        onView(withId(R.id.countdownPicker)).check(matches(withBackgroundColor
                                                                   (overrideFrontModel,
                                                                    ColorProvider.GREEN)));
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.back_fragment_view)).check(matches(withBackgroundColor
                                                                      (overrideFrontModel,
                                                                       ColorProvider.GREEN)));
    }

    @Test
    public void backgroundColorStaysSameWhenGoingBack() {
        onView(withId(R.id.countdownPicker)).check(matches(withBackgroundColor
                                                                   (overrideFrontModel,
                                                                    ColorProvider.GREEN)));
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.back_fragment_view)).check(matches(withBackgroundColor
                                                                      (overrideFrontModel,
                                                                       ColorProvider.GREEN)));
        mActivityRule.getActivity().getFragmentManager().popBackStack();
        onView(withId(R.id.countdownPicker)).check(matches(withBackgroundColor
                                                                   (overrideFrontModel,
                                                                    ColorProvider.GREEN)));
    }


    @Test
    public void onSimpleClickColorWillChange() {
        onView(withId(R.id.countdownPicker)).check(matches(withBackgroundColor
                                                                   (overrideFrontModel,
                                                                    ColorProvider.GREEN)));
        onView(withId(R.id.countdownPicker)).perform(click()).check(matches(withBackgroundColor
                                                                                    (overrideFrontModel, ColorProvider.BLUE)));
    }

}
