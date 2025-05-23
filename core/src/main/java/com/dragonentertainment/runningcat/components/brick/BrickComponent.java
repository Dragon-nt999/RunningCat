package com.dragonentertainment.runningcat.components.brick;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class BrickComponent implements Component, Pool.Poolable {
    public boolean active = false;
    public int index = 0;
    public Vector2 pos;

    @Override
    public void reset() {
        this.active = false;
        this.index = 0;
    }
}
