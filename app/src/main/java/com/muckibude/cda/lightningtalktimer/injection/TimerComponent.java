package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.ui.BackCountdownDisplayFragment;
import com.muckibude.cda.lightningtalktimer.ui.FrontCountdownSelectFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        TimerModule.class
})
@Singleton
public interface TimerComponent {
    FrontCountdownSelectFragment inject(FrontCountdownSelectFragment frontCountdownSelectFragment);

    BackCountdownDisplayFragment inject(BackCountdownDisplayFragment backCountdownDisplayFragment);
}
