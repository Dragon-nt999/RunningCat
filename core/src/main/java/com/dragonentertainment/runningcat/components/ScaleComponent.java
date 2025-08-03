package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ScaleComponent implements Component, Pool.Poolable {
    public float wTarget = 0;
    public float hTarget = 0;
    public float xTarget = 0;
    public float yTarget = 0;
    public boolean isEffect = false;
    public float elapsed = 0f;
    public float duration = 0.3f;
    public float delay = 0.1f;
    public float start = 0;
    public float timeRecochet = 0f;

    @Override
    public void reset() {
        wTarget = 0;
        hTarget = 0;
        xTarget = 0;
        yTarget = 0;
        isEffect = false;
        elapsed = 0f;
        duration = 0.3f;
        delay = 0.1f;
        start = 0;
        timeRecochet = 0f;
    }
}
