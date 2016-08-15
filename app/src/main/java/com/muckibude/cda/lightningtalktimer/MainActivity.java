package com.muckibude.cda.lightningtalktimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.muckibude.cda.lightningtalktimer.injection.DaggerAwareApplication;
import com.muckibude.cda.lightningtalktimer.ui.FrontCountdownSelectFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    FrontCountdownSelectFragment frontFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerAwareApplication.getAppComponent(this).inject(this);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, frontFragment)
                    .commit();
        }
    }
}