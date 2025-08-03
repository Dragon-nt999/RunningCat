package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

import java.util.List;

public class AnimationComponent implements Component, Pool.Poolable {
    public List<Texture> frames = null;
    public float frameDuration = 0.1f;
    public float stateTime = 0.0f;
    public boolean loop = true;
    public boolean stop = false;
    public Texture currentFrame = null;

    @Override
    public void reset() {
        frames = null;
        frameDuration = 0.1f;
        stateTime = 0.0f;
        loop = true;
        stop = false;
        currentFrame = null;
    }
}
