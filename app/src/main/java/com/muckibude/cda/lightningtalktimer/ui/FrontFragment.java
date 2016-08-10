package com.muckibude.cda.lightningtalktimer.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muckibude.cda.lightningtalktimer.R;
import com.muckibude.cda.lightningtalktimer.fragments.BackCountdownDisplayFragment;
import com.muckibude.cda.lightningtalktimer.injection.DaggerAwareApplication;
import com.muckibude.cda.lightningtalktimer.presentation.FrontPresenter;
import com.muckibude.cda.lightningtalktimer.presentation.FrontView;

import java.util.Locale;

import javax.inject.Inject;

public class FrontFragment extends Fragment implements FrontView {

    @Inject
    FrontPresenter frontPresenter;
    private TextView minutesView;
    private TextView secondsView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAwareApplication.getAppComponent(getActivity()).inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frontPresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.front_countdown_setting, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        minutesView = (TextView) view.findViewById(R.id.minutes);
        secondsView = (TextView) view.findViewById(R.id.seconds);
        LinearLayout countdownPicker = (LinearLayout) view.findViewById(R.id.countdownPicker);

        countdownPicker.setOnTouchListener(new OnUpDownListener(frontPresenter));

        ImageView startButtonView = (ImageView) view.findViewById(R.id.startButton);
        startButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackCountdownDisplayFragment backCountdownDisplayFragment = new BackCountdownDisplayFragment();
                Bundle arguments = new Bundle();
                arguments.putInt("minutes", 2);
                arguments.putInt("seconds", 14);
                backCountdownDisplayFragment.setArguments(arguments);
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
                        .replace(R.id.container, backCountdownDisplayFragment)

                        // Add this transaction to the back stack, allowing users to press
                        // Back to get to the front of the card.
                        .addToBackStack(null)

                        // Commit the transaction.
                        .commit();
            }

        });
    }

    @Override
    public void display(int minutes, int seconds) {
        secondsView.setText(String.format(Locale.getDefault(), "%02d", seconds));
        minutesView.setText(String.format(Locale.getDefault(), "%d", minutes));
    }
}
