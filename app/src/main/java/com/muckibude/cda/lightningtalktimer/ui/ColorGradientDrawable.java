package com.muckibude.cda.lightningtalktimer.ui;

import android.graphics.drawable.GradientDrawable;

public class ColorGradientDrawable extends GradientDrawable {

    private int color;

    @Override
    public void setColor(int argb) {
        super.setColor(argb);
        this.color = argb;
    }


    public int getSolidColor() {
        return color;
    }
}
