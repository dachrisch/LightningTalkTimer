package com.muckibude.cda.lightningtalktimer.domain;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

public class PausableCountdownTimerBuilder {

    public PausableCountdownTimer build(CountdownEntity initialCountdownEntity) {
        PausableCountdownTimer pausableOneSecondCountdownTimer = new PausableOneSecondCountdownTimer(initialCountdownEntity);
        return pausableOneSecondCountdownTimer;
    }

}
