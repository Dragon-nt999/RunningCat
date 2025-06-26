package com.dragonentertainment.runningcat.utils;

import com.dragonentertainment.runningcat.components.CollisionComponent;

public class CalculateCollision {
    public static final float MARGIN = 0.5f;

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

        float aR = (a.bounds.x + a.bounds.width);
        float aB = a.bounds.y;
        float aT = aB + a.bounds.height;

        float bL = b.bounds.x;
        float bR = bL + b.bounds.width;
        float bB = b.bounds.y;

        return aR > bL + MARGIN
            && aR <= bR + MARGIN
            && aT <= bB + MARGIN
            && aT >= bB - MARGIN;

    }
    /*
    * Check Collision from right
    * */
    public static boolean aabOverlapRight(CollisionComponent a,
                                          CollisionComponent b) {
        return false;
    }
}
