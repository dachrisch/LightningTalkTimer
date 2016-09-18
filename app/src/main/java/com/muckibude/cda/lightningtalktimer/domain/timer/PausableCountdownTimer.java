package com.muckibude.cda.lightningtalktimer.domain.timer;

public interface PausableCountdownTimer {
    void start();

    void pause();

    void resume();

    boolean isRunning();

    void registerOnFinishedListener(OnFinishedListener onFinishedListener);
}
