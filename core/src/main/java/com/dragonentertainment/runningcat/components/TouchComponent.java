package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class TouchComponent implements Component, Pool.Poolable {
    public boolean isPressed = false;
    public float pressDuration = 0.0f;
    public long pressStartTime = 0;

    @Override
    public void reset() {
        isPressed = false;
        pressDuration = 0.0f;
        pressStartTime = 0;
    }
}
