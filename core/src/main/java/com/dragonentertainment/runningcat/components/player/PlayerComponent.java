package com.dragonentertainment.runningcat.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.dragonentertainment.runningcat.enums.CatState;

public class PlayerComponent implements Component, Pool.Poolable {
    public CatState state;
    public boolean isOnBrick = false;
    @Override
    public void reset() {

    }
}
