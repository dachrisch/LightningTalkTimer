package com.muckibude.cda.lightningtalktimer.domain;

import com.muckibude.cda.lightningtalktimer.data.MinutesSecondsEntity;

import javax.inject.Inject;

public class FrontModel {

    private MinutesSecondsEntity minutesSecondsEntity;

    @Inject
    public FrontModel(MinutesSecondsEntity minutesSecondsEntity) {
        this.minutesSecondsEntity = minutesSecondsEntity;
    }

    public void increase15Seconds() {
        this.minutesSecondsEntity.incrementSeconds(15);
    }

    public void decrease15Seconds() {
        this.minutesSecondsEntity.decrementSeconds(15);
    }

    public MinutesSecondsEntity getMinutesSecondsEntity() {
        return minutesSecondsEntity;
    }
}
