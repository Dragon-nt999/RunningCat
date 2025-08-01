package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;

public class TouchComponent implements Component {
    public boolean isPressed = false;
    public float pressDuration = 0.0f;
    public long pressStartTime = 0;
}
