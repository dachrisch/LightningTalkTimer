package com.muckibude.cda.lightningtalktimer.domain;

import java.util.ArrayList;

public class ColorProvider extends ArrayList<Integer> {

    public ColorProvider() {
        add(toColor("77ff77"));
        add(toColor("77ddff"));
        add(toColor("dd77ff"));
    }

    @Override
    public Integer get(int index) {
        return super.get(index % size());
    }

    public static int toColor(String color) {
        return Integer.parseInt(color, 16) + 0xff000000;
    }

}
