package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class AnimationComponent implements Component {
    public List<Texture> frames;
    public float frameDuration = 0.1f;
    public float stateTime = 0.0f;
    public boolean loop = true;
    public Texture currentFrame;
}
