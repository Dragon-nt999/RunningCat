package com.dragonentertainment.runningcat.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class PlayerComponent implements Component, Pool.Poolable {
    public Vector2 initialPosition = new Vector2();
    @Override
    public void reset() {

    }
}
