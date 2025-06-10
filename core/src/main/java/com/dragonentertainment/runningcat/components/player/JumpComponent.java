package com.dragonentertainment.runningcat.components.player;

import com.badlogic.ashley.core.Component;

public class JumpComponent implements Component {
    public float jumpForce = 0.0f;
    public boolean hasJumped = false;
    public boolean forceMaxed = false;
}
