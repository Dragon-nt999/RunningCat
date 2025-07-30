package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GameGrid {
    public static final int CELL_SIZE = 1;
    public static final int WORLD_WIDTH = 9;
    public static final int WORLD_HEIGHT = 18;
    private static final float PIXEL_PER_UNIT_X = (float) Gdx.graphics.getWidth() / WORLD_WIDTH;
    private static final float PIXEL_PER_UNIT_Y = (float) Gdx.graphics.getHeight() / WORLD_HEIGHT;
    public static final List<Vector2> allPositions = new ArrayList<>();

    public static float toGridWidth(float textureWith) {
        return textureWith / PIXEL_PER_UNIT_X;
    }
    public static float toGridHeight(float textureHeight) {
        return textureHeight / PIXEL_PER_UNIT_Y;
    }

    public static float PixelRatio() {
        return Math.abs(PIXEL_PER_UNIT_Y - PIXEL_PER_UNIT_X);
    }

    /*
    * Get All position on Grid
    * */
    public static void allPosition() {
        for(int y = 0; y <= GameGrid.WORLD_HEIGHT; y++) {
            for(int x = GameGrid.WORLD_WIDTH; x <= GameGrid.WORLD_WIDTH * 2; x++) {
                allPositions.add(new Vector2(x, y));
            }
        }
    }
}
