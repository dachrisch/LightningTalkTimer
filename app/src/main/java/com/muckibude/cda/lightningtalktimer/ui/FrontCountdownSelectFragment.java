package com.muckibude.cda.lightningtalktimer.ui;

import android.app.Fragment;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muckibude.cda.lightningtalktimer.R;
import com.muckibude.cda.lightningtalktimer.injection.DaggerAwareApplication;
import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;
import com.muckibude.cda.lightningtalktimer.presentation.FrontView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrontCountdownSelectFragment extends Fragment implements FrontView {

    @Inject
    FrontPresenter frontPresenter;
    @Inject
    BackCountdownDisplayFragment backFragment;

    @BindView(R.id.minutes)
    TextView minutesView;
    @BindView(R.id.seconds)
    TextView secondsView;
    @BindView(R.id.countdownPicker)
    LinearLayout countdownPicker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAwareApplication.getAppComponent(getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.front_countdown_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        frontPresenter.setView(this);
        countdownPicker.setOnTouchListener(new OnUpDownListener(frontPresenter));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        frontPresenter.toggleColor();
    }

    @OnClick(R.id.startButton)
    public void startCountdown() {
        frontPresenter.startCountdown();
        frontPresenter.setSelectedColor(getCurrentColor());
        getFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, backFragment)

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();
    }

    @Override
    public void display(int minutes, int seconds) {
        secondsView.setText(String.format(Locale.getDefault(), "%02d", seconds));
        minutesView.setText(String.format(Locale.getDefault(), "%d", minutes));
    }

    @Override
    public void switchPickerColorTo(Integer color) {
        ((GradientDrawable) countdownPicker.getBackground()).setColor(color);
    }

    @Override
    public int getCurrentColor() {
        return ((GradientDrawable) countdownPicker.getBackground()).getColor().getDefaultColor();
    }
}
