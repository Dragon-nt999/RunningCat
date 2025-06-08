package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class TransformComponent implements Component, Pool.Poolable {
    public final Vector2 position = new Vector2();
    public float width;
    public float height;
    @Override
    public void reset() {
        position.set(0, 0);
    }

    @Override
    public String toString() {
        return "Transform[pos=" + position + ", width=" + width + ", height=" + height + "]";
    }
}
