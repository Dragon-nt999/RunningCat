package com.dragonentertainment.runningcat.components.brick;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class BrickComponent implements Component, Pool.Poolable {
    public boolean active = false;
    public Vector2 index = new Vector2();

    @Override
    public void reset() {
        this.active = false;
    }
}
