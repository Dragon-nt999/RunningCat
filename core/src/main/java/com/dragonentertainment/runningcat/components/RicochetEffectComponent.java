package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class RicochetEffectComponent implements Component, Pool.Poolable {
    public float duration = 0.5f;
    public float time = 0f;
    public float frequency = 30f;
    public float shakeAmount = 0.05f;
    public boolean isEffect = false;

    @Override
    public void reset() {
        duration = 0.5f;
        time = 0f;
        frequency = 30f;
        shakeAmount = 0.05f;
        isEffect = false;
    }
}
