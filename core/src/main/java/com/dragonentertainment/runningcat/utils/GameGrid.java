package com.dragonentertainment.runningcat.utils;

public class GameGrid {
    public static final float CELL_SIZE = 1f;
    public static final float GRID_WIDTH = 9;
    public static final float GRID_HEIGHT = 16;

    public static float getGridX(int cellX) {
        return cellX * CELL_SIZE;
    }
    public static float getGridY(int cellY) {
        return cellY * CELL_SIZE;
    }
    public static int toGridX(float worldX) {
        return (int) (worldX / CELL_SIZE);
    }
    public static int toGridY(float wordY) {
        return (int) (wordY / CELL_SIZE);
    }
}
