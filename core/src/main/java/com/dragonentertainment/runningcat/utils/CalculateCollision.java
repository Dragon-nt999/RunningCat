package com.dragonentertainment.runningcat.utils;

import com.dragonentertainment.runningcat.components.CollisionComponent;

public class CalculateCollision {
    private static final float MARGIN = 0.5f;

    /**
     * Check Collision from Top
     */

    public static boolean aabbOverlapTop(CollisionComponent a,
                                         CollisionComponent b) {
        float aR = (a.bounds.x + a.bounds.width);
        float aB = a.bounds.y;

        float bL = b.bounds.x;
        float bR = bL + b.bounds.width;
        float bB = b.bounds.y;
        float bT = bB + b.bounds.height;

        return aR > bL
                && aR <= bR + MARGIN
                && aB <= bT + MARGIN
                && aB >= bT - MARGIN;
    }

    /*
     * Check Collision from bottom
     * */
    public static boolean aabbOverlapBottom(CollisionComponent a,
                                            CollisionComponent b) {
        /*float aRight = (a.bounds.x + a.bounds.width);
        float aTop = a.bounds.y + a.bounds.height;

        float bLeft = b.bounds.x;
        float bRight = bLeft + b.bounds.width;
        float bBot = b.bounds.y;
        float bTop = bBot + b.bounds.height;

        return aRight <= bRight
                && aRight >= bLeft + MARGIN
                && aTop >= bBot + MARGIN
                && aTop <= bTop - MARGIN;*/
        return false;

    }
    /*
    * Check Collision from right
    * */
    public static boolean aabOverlapRight(CollisionComponent a,
                                          CollisionComponent b) {
        return false;
    }
}
