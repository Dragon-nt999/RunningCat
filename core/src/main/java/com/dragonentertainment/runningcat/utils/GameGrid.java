package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;

public class GameGrid {
    public static final int CELL_SIZE = 1;
    public static final int WORLD_WIDTH = 9;
    public static final int WORLD_HEIGHT = 16;
    private static final float PIXEL_PER_UNIT = (float) Gdx.graphics.getHeight() / WORLD_HEIGHT;

    public static int toGridWidth(float textureWith) {
        return (int) (textureWith / PIXEL_PER_UNIT);
    }
    public static int toGridHeight(float textureHeight) {
        return (int) (textureHeight / PIXEL_PER_UNIT);
    }
}
