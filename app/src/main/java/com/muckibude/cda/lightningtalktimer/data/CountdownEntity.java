package com.muckibude.cda.lightningtalktimer.data;


import java.io.Serializable;
import java.util.Locale;

public class CountdownEntity implements Serializable {
    private int seconds;
    private int minutes;
    private EntityChangeListener entityChangeListener = new EmptyCountdownEntityChangeListener();

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
        entityChangeListener.inform(this);
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
        entityChangeListener.inform(this);
    }


    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    public int inMillis() {
        return (minutes * 60 + seconds) * 1000;
    }

    public void registerEntityChangeListener(EntityChangeListener entityChangeListener) {
        this.entityChangeListener = entityChangeListener;
    }

    public void updateFromMillis(long millisRemaining) {
        minutes = ((int) millisRemaining) / 1000 / 60;
        seconds = (int) (Math.ceil(Double.valueOf((int) millisRemaining) / 1000.0) % 60);
        entityChangeListener.inform(this);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CountdownEntity)) {
            return false;
        }
        CountdownEntity otherCountdownEntity = ((CountdownEntity) other);
        if (otherCountdownEntity.minutes != this.minutes) {
            return false;
        }
        return otherCountdownEntity.seconds == this.seconds;
    }
}
