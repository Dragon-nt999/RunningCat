package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;

public class ScaleComponent implements Component {
    public float wTarget = 0;
    public float hTarget = 0;
    public float xTarget = 0;
    public float yTarget = 0;
    public boolean isEffect = false;
    public float elapsed = 0f;
    public float duration = 0.3f;
    public float delay = 0.1f;
    public float start = 0;
    public float timeRecochet = 0f;
}
