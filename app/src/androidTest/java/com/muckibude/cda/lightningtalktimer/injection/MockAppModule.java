package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.domain.FrontModel;
import com.muckibude.cda.lightningtalktimer.ui.BackCountdownDisplayFragment;

public class MockAppModule extends TimerModule {

    private FrontModel overrideFrontModel;
    private BackCountdownDisplayFragment overrideBackView;

    @Override
    public FrontModel providesFrontModel() {
        return overrideFrontModel != null ? overrideFrontModel : super.providesFrontModel();
    }

    @Override
    public BackCountdownDisplayFragment providesBackCountdownDisplayFragment() {
        return overrideBackView != null ? overrideBackView : super.providesBackCountdownDisplayFragment();
    }

    public void setOverrideFrontModel(FrontModel overrideFrontModel) {
        this.overrideFrontModel = overrideFrontModel;
    }

    public void setOverrideBackView(BackCountdownDisplayFragment overrideBackView) {
        this.overrideBackView = overrideBackView;
    }
}
