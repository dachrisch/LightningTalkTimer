package com.muckibude.cda.lightningtalktimer.data;

public interface EntityChangeListener<T> {
    void inform(T changedEntity);
}
