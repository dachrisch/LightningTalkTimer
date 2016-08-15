package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.ui.BackFragment;
import com.muckibude.cda.lightningtalktimer.ui.FrontFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        TimerModule.class
})
@Singleton
public interface TimerComponent {
    FrontFragment inject(FrontFragment frontFragment);

    BackFragment inject(BackFragment backFragment);
}
