package com.dragonentertainment.runningcat.utils;

import com.dragonentertainment.runningcat.components.TransformComponent;

public class CalculateCollision {
    private static final float MARGIN = 0.05f;

    public static boolean isStillOnBrick(TransformComponent trans1, TransformComponent trans2) {

        float obj1Center = trans1.position.x + trans1.width / 2f;
        float object2Right = trans2.position.x + trans2.width;

        boolean isOnTop = Math.abs(trans1.position.y - (trans2.position.y + trans2.height)) <= MARGIN;
        boolean isOnSide = object2Right >= trans2.position.x + MARGIN && obj1Center <= object2Right - MARGIN;

        return isOnTop && isOnSide;
    }
}
