package com.muckibude.cda.lightningtalktimer.domain;

import android.util.Log;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.data.EntityChangeListener;

import java.util.Locale;

import javax.inject.Inject;

public class FrontModel {

    private static final String TAG = "FrontModel";
    private final CountdownEntity countdownEntity;

    private ColorProvider colors;
    private Integer currentColor = 0;

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

    public Integer getNextColor() {
        this.currentColor = colors.get(colors.indexOf(currentColor) + 1);
        Log.d(TAG, String.format(Locale.getDefault(), "next color: %d", this.currentColor));
        return this.currentColor;
    }

    @Inject
    public void setColors(ColorProvider colors) {
        this.colors = colors;
        getNextColor();
    }

    public Integer getCurrentColor() {
        return currentColor;
    }

    public void registerEntityChangeListener(EntityChangeListener<CountdownEntity>
                                                     entityChangeListener) {
        countdownEntity.registerEntityChangeListener(entityChangeListener);
    }

    public void increaseOneMinute() {
        countdownEntity.incrementSeconds(60);
    }

    public void decreaseOneMinute() {
        countdownEntity.decrementSeconds(60);
    }
}
