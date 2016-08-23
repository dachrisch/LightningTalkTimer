package com.muckibude.cda.lightningtalktimer.domain;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

import javax.inject.Inject;

public class FrontModel {

    private final CountdownEntity countdownEntity;

    @Inject
    public FrontModel(CountdownEntity minutesSecondsEntity) {
        this.countdownEntity = minutesSecondsEntity;
    }

    public void increase15Seconds() {
        this.countdownEntity.incrementSeconds(15 - countdownEntity.getSeconds() % 15);
    }

    public void decrease15Seconds() {
        if (0 == countdownEntity.getSeconds() % 15) {
            countdownEntity.decrementSeconds(15);
        } else {
            this.countdownEntity.decrementSeconds(countdownEntity.getSeconds() % 15);
        }
    }

    public CountdownEntity getCountdownEntity() {
        return countdownEntity;
    }
}
