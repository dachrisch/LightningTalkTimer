package com.muckibude.cda.lightningtalktimer.domain;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

public interface OnSecondListener {
    void inform(CountdownEntity countdownEntity);
}
