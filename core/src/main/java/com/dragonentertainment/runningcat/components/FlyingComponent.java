package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class FlyingComponent implements Component, Pool.Poolable {
    public Vector2 target = new Vector2();
    public Vector2 start = new Vector2();
    public float duration = 1f;
    public float elapsed = 0f;

    @Override
    public void reset() {
        target = new Vector2();
        start = new Vector2();
        duration = 1f;
        elapsed = 0f;
    }
}
