package com.dragonentertainment.runningcat.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.dragonentertainment.runningcat.enums.PlayerState;

public class PlayerComponent implements Component {
    public PlayerState state;
    public boolean isOnBrick = false;
    public Vector3 position;
}
