package com.muckibude.cda.lightningtalktimer.data;

public interface EntityChangeListener<T extends Object> {
    void inform(T changedEntity);
}
