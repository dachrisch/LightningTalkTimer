package com.muckibude.cda.lightningtalktimer.domain;

public interface PausableCountdownTimer {
    PausableCountdownTimer start();

    void pause();

    void resume();

    boolean isRunning();
}
