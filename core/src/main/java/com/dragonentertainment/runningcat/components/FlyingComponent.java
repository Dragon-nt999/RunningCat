package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class FlyingComponent implements Component {
    public Vector2 target = new Vector2();
    public float duration = 1f;
    public float elapsed = 0f;
}
