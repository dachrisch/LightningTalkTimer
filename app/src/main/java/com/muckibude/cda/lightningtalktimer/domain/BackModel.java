package com.muckibude.cda.lightningtalktimer.domain;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

import javax.inject.Inject;

public class BackModel {

    private final CountdownEntity countdownEntity;
    private Integer backgroundColor;

    @Inject
    public BackModel(CountdownEntity countdownEntity) {
        this.countdownEntity = countdownEntity;
    }

    public int getMinutes() {
        return countdownEntity.getMinutes();
    }

    public int getSeconds() {
        return countdownEntity.getSeconds();
    }

    public void setInitialCountdown(CountdownEntity countdownEntity) {
        this.countdownEntity.updateWith(countdownEntity);
    }

    public void updateCountdown(int minutes, int seconds) {
        countdownEntity.update(minutes, seconds);
    }

    public CountdownEntity getCountdownEntity() {
        return countdownEntity;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getBackgroundColor() {
        return backgroundColor;
    }

}

