package com.muckibude.cda.lightningtalktimer.domain;

import android.util.Log;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

public class FrontModel {

    private static final String TAG = "FrontModel";
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

    public Integer getNextColor(Integer currentColor) {
        ArrayList<Integer> colors = new ArrayList<Integer>() {
            @Override
            public Integer get(int index) {
                return super.get(index % size());
            }
        };
        colors.add(toColor("ff4fff"));
        colors.add(toColor("ffff4f"));
        colors.add(toColor("4fffff"));

        Integer nextColor = colors.get(colors.indexOf(currentColor) + 1);
        Log.d(TAG, String.format(Locale.getDefault(), "next color: %d", nextColor));
        return nextColor;
    }

    public static int toColor(String color) {
        return Integer.parseInt(color, 16) + 0xff000000;
    }
}
