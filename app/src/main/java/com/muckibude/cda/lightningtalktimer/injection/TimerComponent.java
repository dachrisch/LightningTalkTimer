package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.MainActivity;
import com.muckibude.cda.lightningtalktimer.ui.BackCountdownDisplayFragment;
import com.muckibude.cda.lightningtalktimer.ui.FrontCountdownSelectFragment;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ChangeColorAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ToggleTimerAction;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        TimerModule.class
})
@Singleton
public interface TimerComponent {
    FrontCountdownSelectFragment inject(FrontCountdownSelectFragment frontCountdownSelectFragment);

    BackCountdownDisplayFragment inject(BackCountdownDisplayFragment backCountdownDisplayFragment);

    MainActivity inject(MainActivity mainActivity);

    ToggleTimerAction inject(ToggleTimerAction toggleTimerAction);

    ChangeColorAction inject(ChangeColorAction changeColorAction);
}
