package com.muckibude.cda.lightningtalktimer.domain;

import java.util.ArrayList;

public class ColorProvider extends ArrayList<Integer> {

    public static final int GREEN = toColor("7cf77c");
    public static final int BLUE = toColor("7cc5f5");
    public static final int VIOLET = toColor("dd7ad9");

    public ColorProvider() {
        add(GREEN);
        add(BLUE);
        add(VIOLET);
    }

    @Override
    public Integer get(int index) {
        return super.get(index % size());
    }

    public static int toColor(String color) {
        return Integer.parseInt(color, 16) + 0xff000000;
    }

}
