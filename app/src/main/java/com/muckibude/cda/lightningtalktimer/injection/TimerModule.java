package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.ColorProvider;
import com.muckibude.cda.lightningtalktimer.domain.PausableCountdownTimerBuilder;
import com.muckibude.cda.lightningtalktimer.ui.BackCountdownDisplayFragment;
import com.muckibude.cda.lightningtalktimer.ui.FrontCountdownSelectFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class TimerModule {

    @Provides
    public CountdownEntity providesCountdownEntity() {
        return new CountdownEntity(2, 15);
    }

    @Provides
    public ColorProvider providesColorProvider() {
        return new ColorProvider();
    }

    @Provides
    public FrontCountdownSelectFragment providesFrontCountdownSelectFragment() {
        return new FrontCountdownSelectFragment();
    }

    @Provides
    public BackCountdownDisplayFragment providesBackCountdownDisplayFragment() {
        return new BackCountdownDisplayFragment();
    }
    @Provides
    public PausableCountdownTimerBuilder providesPausableCountdownTimerBuilder() {
        return new PausableCountdownTimerBuilder();
    }


}
