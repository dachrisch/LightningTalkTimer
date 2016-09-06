package com.muckibude.cda.lightningtalktimer.domain.timer;

public class NotStartableCountdownTimer implements PausableCountdownTimer {
    @Override
    public PausableCountdownTimer start() {
        return this;
    }

    @Override
    public void pause() {
        // pass
    }

    @Override
    public void resume() {
        throw new IllegalStateException("not startable");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void registerOnFinishedListener(OnFinishedListener onFinishedListener) {
        // pass
    }
}
