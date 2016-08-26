package com.muckibude.cda.lightningtalktimer.presentation;

public interface BackView {
    void display(int minutes, int seconds);

    void pause();

    void resume();

    void display(int seconds);

    void setBackgroundColor(int backgroundColor);
}
