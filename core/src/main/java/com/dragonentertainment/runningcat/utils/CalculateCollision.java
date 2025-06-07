package com.dragonentertainment.runningcat.utils;

import com.dragonentertainment.runningcat.components.CollisionComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;

public class CalculateCollision {
    private static final float MARGIN = 0.05f;

    public static boolean aabbOverlap(TransformComponent aTransform, CollisionComponent aCollider,
                                      TransformComponent bTransform, CollisionComponent bCollider)

    {
        float ax = aTransform.position.x;
        float ay = aTransform.position.y;
        float aw = aCollider.bounds.width / 2f;
        float ah = aCollider.bounds.height;

        float bx = bTransform.position.x;
        float by = bTransform.position.y;
        float bw = bCollider.bounds.width;
        float bh = bCollider.bounds.height;

        return ax < bx + bw + MARGIN && ax + aw > bx - MARGIN &&
            ay < by + bh + MARGIN && ay + ah > by - MARGIN;
    }
}
