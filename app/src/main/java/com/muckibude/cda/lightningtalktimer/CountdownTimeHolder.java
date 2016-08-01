package com.muckibude.cda.lightningtalktimer;

import android.util.Log;
import android.widget.TextView;

public class CountdownTimeHolder {

    private final android.widget.TextView secondsView;
    private final android.widget.TextView minutesView;
    private int seconds;
    private int minutes;
    private static final String TAG = "CountdownTimeHolder";

    public CountdownTimeHolder(TextView minutesView, TextView secondsView) {
        this.secondsView = secondsView;
        this.minutesView = minutesView;

        seconds = Integer.parseInt(String.valueOf(secondsView.getText()));
        minutes = Integer.parseInt(String.valueOf(minutesView.getText()));
    }

    public void inc(int delta) {
        seconds += delta;
        if (seconds >= 60) {
            seconds -= 60;
            minutes++;
        }

        Log.d(TAG, String.format("inc(%d:%d)", minutes, seconds));
        secondsView.setText(String.format("%02d", seconds));
        minutesView.setText(String.format("%d", minutes));
    }

    public void dec(int delta) {
        seconds -= delta;
        if (minutes > 0) {
            if (seconds < 0) {
                seconds += 60;
                minutes--;
            }
        } else {
            if (seconds < 0) {
                seconds = 0;
            }
        }
        Log.d(TAG, String.format("dec(%d:%d)", minutes, seconds));
        secondsView.setText(String.format("%02d", seconds));
        minutesView.setText(String.format("%d", minutes));
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }
}
