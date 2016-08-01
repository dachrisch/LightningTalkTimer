package com.muckibude.cda.lightningtalktimer.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muckibude.cda.lightningtalktimer.R;
import com.muckibude.cda.lightningtalktimer.countdown.PauseableCountdownTimer;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BackCountdownDisplayFragment extends Fragment {
    private static final String TAG = "BackCountdownDisplay";
    private PauseableCountdownTimer countDownTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.back_countdown_display, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        final TextView bigNumberView = (TextView) view.findViewById(R.id.big_number_text_view);
        // http://stackoverflow.com/questions/6253528/font-size-too-large-to-fit-in-cache
        bigNumberView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        final TextView smallNumberView = (TextView) view.findViewById(R.id.small_number_text_view);

        int initialMinutes = getArguments().getInt("minutes");
        int initialSeconds = getArguments().getInt("seconds");
        countDownTimer = new PauseableCountdownTimer((initialMinutes * 60 + initialSeconds) * 1000, 1000) {
            @Override
            public void onFinish() {
                Log.d(TAG, "finished");
            }

            @Override
            public void onTick(long millisUntilFinished) {
                long remainingMinutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long remainingSeconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - 60 * remainingMinutes;
                bigNumberView.setText(String.format(Locale.getDefault(), "%d", remainingMinutes));
                smallNumberView.setText(String.format(Locale.getDefault(), "%02d", remainingSeconds));
                Log.d(TAG, String.format(Locale.getDefault(), "remaining time %d:%02d", remainingMinutes, remainingSeconds));
            }

        };
        countDownTimer.start();

        view.findViewById(R.id.pauseButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (countDownTimer.isRunning()) {
                    countDownTimer.pause();
                } else {
                    countDownTimer.resume();
                }
            }
        });
    }
}
