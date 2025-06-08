package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;

public class TouchComponent implements Component {
    public boolean isPressed = false;
    public float pressDuration = 0.0f;

    public float touchX = 0.0f;
    public float touchY = 0.0f;
}
