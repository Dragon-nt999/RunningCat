package com.dragonentertainment.runningcat.components.player;

import com.badlogic.ashley.core.Component;

public class JumpComponent implements Component {
    public float startY = 0.0f; // Tracking cat position y when on brick
    public float endY = 0.0f; // Tracking cat position y during jump
    public float maxJumpY = 0.0f; // Tracking high level cat jump at time

}
