package com.muckibude.cda.lightningtalktimer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cda on 31.07.16.
 */
public class BackCountdownDisplayFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.back_countdown_display, container, false);
        return view;
    }

    /*int seconds = countdownTimeHolder.getSeconds();
    int minutes = countdownTimeHolder.getMinutes();
    final int countdownTime = minutes * 60 + seconds;
    new CountDownTimer(countdownTime * 1000, 1) {
        @Override
        public void onTick(long millisUntilFinished) {
            countdownView.setText(String.format("%d", millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            countdownView.setText("finished");
        }
    }.start();*/
}
