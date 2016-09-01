package com.muckibude.cda.lightningtalktimer.data;

import java.util.Locale;

public class CountdownEntity {
    private int seconds;
    private int minutes;

    public CountdownEntity(int minutes, int seconds) {
        this.seconds = seconds;
        this.minutes = minutes;
    }

    public void incrementSeconds(final int deltaSeconds) {
        this.seconds += deltaSeconds;
        if (this.seconds >= 60) {
            this.seconds -= 60;
            this.minutes++;
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void decrementSeconds(final int deltaSeconds) {
        this.seconds -= deltaSeconds;
        if (minutes > 0) {
            if (this.seconds < 0) {
                this.seconds += 60;
                minutes--;
            }
        } else {
            if (this.seconds < 0) {
                this.seconds = 0;
            }
        }
    }

    public void updateWith(CountdownEntity countdownEntity) {
        this.minutes = countdownEntity.getMinutes();
        this.seconds = countdownEntity.getSeconds();
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    public void update(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }
}
