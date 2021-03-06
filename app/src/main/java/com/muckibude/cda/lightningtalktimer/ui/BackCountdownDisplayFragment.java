package com.muckibude.cda.lightningtalktimer.ui;

import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.muckibude.cda.lightningtalktimer.R;
import com.muckibude.cda.lightningtalktimer.injection.DaggerAwareApplication;
import com.muckibude.cda.lightningtalktimer.presentation.BackPresenter;
import com.muckibude.cda.lightningtalktimer.presentation.BackView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackCountdownDisplayFragment extends Fragment implements BackView {
    private static final String TAG = BackCountdownDisplayFragment.class.getName();

    @Inject
    BackPresenter backPresenter;
    @BindView(R.id.big_number_text_view)
    TextView bigNumberView;
    @BindView(R.id.small_number_text_view)
    TextView smallNumberView;
    @BindView(R.id.pauseButton)
    ImageView pauseButton;

    @BindView(R.id.back_fragment_view)
    View background;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAwareApplication.getAppComponent(getActivity()).inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.back_countdown_display, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        backPresenter.setView(this);
        // http://stackoverflow.com/questions/6253528/font-size-too-large-to-fit-in-cache
        bigNumberView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        backPresenter.getFrontArguments(getArguments());
        backPresenter.startTimer();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        backPresenter.applyBackgroundColor();
    }

    @OnClick(R.id.pauseButton)
    public void toggleTimer() {
        backPresenter.toggleTimer();
    }

    @Override
    public void display(int minutes, int seconds) {
        bigNumberView.setText(String.format(Locale.getDefault(), "%d", minutes));
        smallNumberView.setText(String.format(Locale.getDefault(), "%02d", seconds));
        Log.d(TAG, String.format(Locale.getDefault(), "remaining time %d:%02d", minutes, seconds));

    }

    @Override
    public void display(int seconds) {
        smallNumberView.setVisibility(View.INVISIBLE);
        bigNumberView.setText(String.format(Locale.getDefault(), "%d", seconds));
        Log.d(TAG, String.format(Locale.getDefault(), "remaining time %02d", seconds));
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        ((ColorDrawable) background.getBackground()).setColor(backgroundColor);
    }

    @Override
    public void blinkScreen() {
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        background.startAnimation(animation);
        pauseButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        backPresenter.stopTimer();
        backPresenter.setView(null);
    }

    @Override
    public void pause() {
        pauseButton.setImageResource(R.drawable.play_overlay_icon);
    }

    @Override
    public void resume() {
        pauseButton.setImageResource(R.drawable.pause_overlay_icon);
    }

}
