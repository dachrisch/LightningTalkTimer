package com.muckibude.cda.lightningtalktimer.data;

public class MinutesSecondsEntity {
    private int seconds;
    private int minutes;

    public MinutesSecondsEntity(int minutes, int seconds) {
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
}
