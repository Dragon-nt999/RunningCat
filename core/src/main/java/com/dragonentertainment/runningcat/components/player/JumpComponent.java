package com.dragonentertainment.runningcat.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class JumpComponent implements Component, Pool.Poolable {
    public float startY = 0.0f; // Tracking cat position y when on brick
    public float endY = 0.0f; // Tracking cat position y during jump

    @Override
    public void reset() {
        startY = 0.0f; // Tracking cat position y when on brick
        endY = 0.0f; // Tracking cat position y during jump
    }
}
