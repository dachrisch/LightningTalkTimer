package com.muckibude.cda.lightningtalktimer.domain;

import com.muckibude.cda.lightningtalktimer.data.MinutesSecondsEntity;

import javax.inject.Inject;

public class BackModel {

    private final MinutesSecondsEntity minutesSecondsEntity;

    @Inject
    public BackModel(MinutesSecondsEntity minutesSecondsEntity) {
        this.minutesSecondsEntity = minutesSecondsEntity;
    }


    public int getMinutes() {
        return minutesSecondsEntity.getMinutes();
    }

    public int getSeconds() {
        return minutesSecondsEntity.getSeconds();
    }

    public void decrementOneSecond() {
        minutesSecondsEntity.decrementSeconds(1);
    }
}
