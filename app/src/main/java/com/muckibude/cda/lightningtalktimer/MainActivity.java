package com.muckibude.cda.lightningtalktimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.muckibude.cda.lightningtalktimer.ui.FrontFragment;

public class MainActivity extends AppCompatActivity {

    private ImageView startButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new FrontFragment())
                    .commit();
        }
    }
}