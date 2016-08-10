package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.data.MinutesSecondsEntity;

import dagger.Module;
import dagger.Provides;

@Module
public class TimerModule {

    @Provides
    public MinutesSecondsEntity providesMinutesSecondsEntity() {
        return new MinutesSecondsEntity(2, 15);
    }
}
