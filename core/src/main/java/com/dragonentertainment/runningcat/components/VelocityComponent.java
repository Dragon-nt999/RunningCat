package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class VelocityComponent implements Component, Pool.Poolable {
    public final Vector2 velocity = new Vector2();
    @Override
    public void reset() {
        this.velocity.set(0, 0);
    }
}
