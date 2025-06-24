package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;

public class RicochetEffectComponent implements Component {
    public float duration = 0.1f;
    public float time = 0f;
    public boolean triggered = false;
}
