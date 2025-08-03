package com.dragonentertainment.runningcat.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import com.dragonentertainment.runningcat.enums.CatState;

public class PlayerComponent implements Component, Pool.Poolable {
    public CatState state = null;
    public boolean isOnBrick = false;
    public Vector3 position = null;

    @Override
    public void reset() {
        state = null;
        isOnBrick = false;
        position = null;
    }
}
