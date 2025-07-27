package com.dragonentertainment.runningcat.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.dragonentertainment.runningcat.enums.CatState;

public class PlayerComponent implements Component {
    public CatState state;
    public boolean isOnBrick = false;
    public Vector3 position;
}
