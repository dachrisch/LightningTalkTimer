package com.muckibude.cda.lightningtalktimer.injection;

public class TestApp extends DaggerAwareApplication {
    private TimerModule overrideModule;

    @Override
    protected TimerModule getApplicationModule() {
        return overrideModule != null ? overrideModule : super.getApplicationModule();
    }

    public void setOverrideModule(TimerModule overrideModule) {
        this.overrideModule = overrideModule;
    }
}
