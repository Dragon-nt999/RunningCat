package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public class CollisionComponent implements Component, Pool.Poolable {
    public Rectangle bounds = new Rectangle();

    @Override
    public void reset() {
        bounds = new Rectangle();
    }
}
