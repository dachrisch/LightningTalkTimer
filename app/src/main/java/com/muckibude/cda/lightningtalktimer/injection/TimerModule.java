package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.data.MinutesSecondsEntity;
import com.muckibude.cda.lightningtalktimer.ui.BackCountdownDisplayFragment;
import com.muckibude.cda.lightningtalktimer.ui.FrontCountdownSelectFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class TimerModule {

    @Provides
    public MinutesSecondsEntity providesMinutesSecondsEntity() {
        return new MinutesSecondsEntity(2, 15);
    }

    @Provides
    public FrontCountdownSelectFragment providesFrontCountdownSelectFragment() {
        return new FrontCountdownSelectFragment();
    }

    @Provides
    public BackCountdownDisplayFragment providesBackCountdownDisplayFragment() {
        return new BackCountdownDisplayFragment();
    }
}
