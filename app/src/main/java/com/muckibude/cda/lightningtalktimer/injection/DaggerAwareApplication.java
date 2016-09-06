package com.muckibude.cda.lightningtalktimer.injection;

import android.app.Application;
import android.content.Context;

public class DaggerAwareApplication extends Application {
    private TimerComponent component;

    protected TimerModule getApplicationModule() {
        return new TimerModule();
    }


    public static TimerComponent getAppComponent(Context context) {
        DaggerAwareApplication app = (DaggerAwareApplication) context.getApplicationContext();
        if (app.component == null) {
            app.component = DaggerTimerComponent.builder()
                    .timerModule(app.getApplicationModule())
                    .build();
        }
        return app.component;
    }

    public static void clearAppComponent(Context context) {
        DaggerAwareApplication app = (DaggerAwareApplication) context.getApplicationContext();
        app.component = null;
    }
}
