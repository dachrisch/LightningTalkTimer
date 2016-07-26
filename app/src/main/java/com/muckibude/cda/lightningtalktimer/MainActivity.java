package com.muckibude.cda.lightningtalktimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private TextView countdownView;
    private TextView minutesView;
    private TextView secondsView;
    private LinearLayout countdownPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        startButton = (Button) findViewById(R.id.startButton);
        countdownView = (TextView) findViewById(R.id.countdownView);
        minutesView = (TextView) findViewById(R.id.minutes);
        secondsView = (TextView) findViewById(R.id.seconds);
        countdownPicker = (LinearLayout) findViewById(R.id.countdownPicker);

        countdownPicker.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeTop() {
                int seconds = Integer.parseInt(String.valueOf(secondsView.getText()));
                int minutes = Integer.parseInt(String.valueOf(minutesView.getText()));
                seconds += 15;
                if (seconds >= 60) {
                    seconds -= 60;
                    minutes++;
                }
                secondsView.setText(String.format("%02d", seconds));
                minutesView.setText(String.format("%d", minutes));
            }

            @Override
            public void onSwipeBottom() {
                int seconds = Integer.parseInt(String.valueOf(secondsView.getText()));
                int minutes = Integer.parseInt(String.valueOf(minutesView.getText()));
                seconds -= 15;
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
                secondsView.setText(String.format("%02d", seconds));
                minutesView.setText(String.format("%d", minutes));
            }
        });


        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int seconds = Integer.parseInt(String.valueOf(secondsView.getText()));
                int minutes = Integer.parseInt(String.valueOf(minutesView.getText()));
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
                }.start();
            }
        });
    }


}
