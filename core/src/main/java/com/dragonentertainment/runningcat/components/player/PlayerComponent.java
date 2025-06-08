package com.dragonentertainment.runningcat.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.dragonentertainment.runningcat.enums.CatState;

public class PlayerComponent implements Component, Pool.Poolable {
    public float jumpForce = 0.0f;
    public CatState state;
    @Override
    public void reset() {

    }
}
