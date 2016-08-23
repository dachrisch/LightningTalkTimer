package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.BackModel;
import com.muckibude.cda.lightningtalktimer.domain.PauseableMinutesSecondsTimer;
import com.muckibude.cda.lightningtalktimer.ui.BackCountdownDisplayFragment;
import com.muckibude.cda.lightningtalktimer.ui.FrontCountdownSelectFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TimerModule {

    @Provides
    public CountdownEntity providesCountdownEntity() {
        return new CountdownEntity(2, 15);
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
    public PauseableMinutesSecondsTimer providesPauseableMinutesSecondsTimer() {
        return new PauseableMinutesSecondsTimer();
    }

    @Singleton
    @Provides
    public BackModel providesBackModel() {
        return new BackModel(providesCountdownEntity());
    }

}
