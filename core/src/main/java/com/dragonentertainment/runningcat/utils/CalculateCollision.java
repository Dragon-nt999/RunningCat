package com.dragonentertainment.runningcat.utils;

import com.dragonentertainment.runningcat.components.CollisionComponent;

public class CalculateCollision {
    private static final float MARGIN_Y = 0.2f;
    private static final float MARGIN_X = 0.3f;
    public static final float MIN_MARGIN_X = MARGIN_X/3;

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

        return aR > bL + MARGIN_X
                && aR <= bR + MARGIN_X
                && aB <= bT + MARGIN_Y
                && aB >= bT - MARGIN_Y;
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

        return aR > bL + MARGIN_X
            && aR <= bR + MARGIN_X
            && aT < bB + MARGIN_Y
            && aT > bB;

    }

    /*
    * Check Collision from right when Falling
    * */
    public static boolean aabOverlapRightWhenFalling(CollisionComponent a,
                                          CollisionComponent b) {
        float aR = (a.bounds.x + a.bounds.width);
        float aB = a.bounds.y;
        float aT = aB + a.bounds.height;

        float bL = b.bounds.x;
        float bR = bL + b.bounds.width;
        float bB = b.bounds.y;
        float bT = bB + b.bounds.height;

        return (aR > bL + MARGIN_X/3
               && aR < bR + MARGIN_X/3
               && aB < bT
               && aB > bB)
              ||
            (aR > bL + MARGIN_X/3
                && aR < bR + MARGIN_X/3
                && aT < bT
                && aT > bB);
    }

    /*
     * Check Collision from right when Falling
     * */
    public static boolean aabOverlapRightWhenJumping(CollisionComponent a,
                                                 CollisionComponent b) {
        float aR = (a.bounds.x + a.bounds.width);
        float aB = a.bounds.y;
        float aT = aB + a.bounds.height;

        float bL = b.bounds.x;
        float bR = bL + b.bounds.width;
        float bB = b.bounds.y;
        float bT = bB + b.bounds.height;

        return (aR > bL + MARGIN_X/3
            && aR < bR + MARGIN_X/3
            && aT > bB
            && aT < bT)
            ||
            (aR > bL + MARGIN_X/3
                && aR < bR + MARGIN_X/3
                && aB > bB
                && aB < bT);
    }

    /*
     * Check Collision from right when Hit
     * */
    public static boolean aabOverlapRightWhenOverOtherEntity(CollisionComponent a,
                                                                CollisionComponent b) {
        float aR = (a.bounds.x + a.bounds.width);
        float aB = a.bounds.y;

        float bL = b.bounds.x;
       // float bCenter = (bL + b.bounds.width) / 2;
        float bR = bL + b.bounds.width;
        float bB = b.bounds.y;
        float bT = bB + b.bounds.height;

        return aR >= bL + MARGIN_X
                && aR <= bR
                && aB >= bB
                && aB <= bT - MARGIN_Y;
    }
}
