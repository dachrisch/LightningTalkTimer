package com.muckibude.cda.lightningtalktimer.injection;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;
import com.muckibude.cda.lightningtalktimer.domain.ColorProvider;
import com.muckibude.cda.lightningtalktimer.domain.FrontModel;
import com.muckibude.cda.lightningtalktimer.domain.timer.PausableCountdownTimerBuilder;
import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;
import com.muckibude.cda.lightningtalktimer.ui.BackCountdownDisplayFragment;
import com.muckibude.cda.lightningtalktimer.ui.FrontCountdownSelectFragment;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ChangeColorAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.OnHoldToggleTimerViewAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.RebaseViewAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.TimerFactory;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ToggleTimerAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.UpDownMoveAction;
import com.muckibude.cda.lightningtalktimer.ui.gestures.ViewAction;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class TimerModule {

    @Provides
    public FrontCountdownSelectFragment providesFrontCountdownSelectFragment() {
        return new FrontCountdownSelectFragment();
    }

    @Singleton
    @Provides
    public FrontPresenter providesFrontPresenter(FrontModel frontModel) {
        return new FrontPresenter(frontModel);
    }

    @Provides
    public FrontModel providesFrontModel() {
        FrontModel frontModel = new FrontModel(new CountdownEntity(2, 15));
        frontModel.setColors(new ColorProvider());
        return frontModel;
    }

    @Provides
    public BackCountdownDisplayFragment providesBackCountdownDisplayFragment() {
        return new BackCountdownDisplayFragment();
    }

    @Provides
    public PausableCountdownTimerBuilder providesPausableCountdownTimerBuilder() {
        return new PausableCountdownTimerBuilder();
    }

    @Provides
    @IntoSet
    public ViewAction providesUpDownMoveAction() {
        return new UpDownMoveAction();
    }

    @Provides
    @IntoSet
    public ViewAction providesRebaseViewAction() {
        return new RebaseViewAction();
    }

    @Provides
    @IntoSet
    public ViewAction providesToggleTimerAction(FrontPresenter frontPresenter) {
        return new ToggleTimerAction(frontPresenter);
    }

    @Provides
    @IntoSet
    public ViewAction providesChangeColorAction(FrontPresenter frontPresenter) {
        return new ChangeColorAction(frontPresenter);
    }

    @Provides
    @IntoSet
    public ViewAction providesOnHoldToggleTimerViewAction(FrontPresenter frontPresenter,
                                                          TimerFactory timerFactory) {
        return new OnHoldToggleTimerViewAction(frontPresenter, timerFactory);
    }

    @Provides
    @Singleton
    public TimerFactory providesTimerFactory() {
        return new TimerFactory();
    }

}
