package com.muckibude.cda.lightningtalktimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.muckibude.cda.lightningtalktimer.gestures.TimeChangeOnTouchListener;

import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    private ImageView startButtonView;
    private TextView countdownView;
    private LinearLayout countdownPicker;
    private CountdownTimeHolder countdownTimeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        startButtonView = (ImageView) findViewById(R.id.startButton);
        countdownView = (TextView) findViewById(R.id.countdownView);
        TextView minutesView = (TextView) findViewById(R.id.minutes);
        TextView secondsView = (TextView) findViewById(R.id.seconds);
        countdownPicker = (LinearLayout) findViewById(R.id.countdownPicker);

        countdownTimeHolder = new CountdownTimeHolder(minutesView, secondsView);
        countdownPicker.setOnTouchListener(new TimeChangeOnTouchListener(countdownTimeHolder));


        startButtonView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int seconds = countdownTimeHolder.getSeconds();
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
                }.start();
            }
        });
    }


}
