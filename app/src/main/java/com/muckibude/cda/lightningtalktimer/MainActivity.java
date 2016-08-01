package com.muckibude.cda.lightningtalktimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.muckibude.cda.lightningtalktimer.fragments.FrontCountdownSettingFragment;

public class MainActivity extends AppCompatActivity {

    private ImageView startButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FrontCountdownSettingFragment frontCountdownSettingFragment = new FrontCountdownSettingFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, frontCountdownSettingFragment)
                    .commit();
        }
    }
}