package com.dragonentertainment.runningcat.utils;

import com.dragonentertainment.runningcat.components.CollisionComponent;

public class CalculateCollision {
    private static final float MARGIN = 0.05f;

    public static boolean aabbOverlapTop(CollisionComponent aCollider,
                                         CollisionComponent bCollider) {

        float aLeft = aCollider.bounds.x;
        float aBott = aCollider.bounds.y;

        float bLeft = bCollider.bounds.x;
        float bRight = bLeft + bCollider.bounds.width;
        float bBott = bCollider.bounds.y;
        float bTop = bBott + bCollider.bounds.height;

        return aLeft <= bRight + MARGIN && aLeft >= bLeft
                            && aBott <= bTop + MARGIN && aBott >= bTop - MARGIN;

    }
}
