package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.ColorProvider;
import com.muckibude.cda.lightningtalktimer.domain.FrontModel;
import com.muckibude.cda.lightningtalktimer.domain.timer.PausableCountdownTimerBuilder;
import com.muckibude.cda.lightningtalktimer.ui.BackCountdownDisplayFragment;
import com.muckibude.cda.lightningtalktimer.ui.FrontCountdownSelectFragment;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ViewActionBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public class TimerModule {

    @Provides
    public FrontCountdownSelectFragment providesFrontCountdownSelectFragment() {
        return new FrontCountdownSelectFragment();
    }

    @Provides
    public FrontModel providesFrontModel() {
        FrontModel frontModel = new FrontModel(new CountdownEntity(2, 15));
        frontModel.setColors(new ColorProvider());
        return frontModel;
    }

    @Provides
    public BackCountdownDisplayFragment providesBackCountdownDisplayFragment() {
        return new BackCountdownDisplayFragment();
    }
    @Provides
    public PausableCountdownTimerBuilder providesPausableCountdownTimerBuilder() {
        return new PausableCountdownTimerBuilder();
    }

    @Provides
    public ViewActionBuilder providesViewActionBuilder() {
        return new ViewActionBuilder();
    }
}
