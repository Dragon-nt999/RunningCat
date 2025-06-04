package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class SpatialGrid<T> {
    /*private final int cellSize;
    private final Map<Point, Array<T>> grid = new HashMap<>();

    public SpatialGrid(int cellSize) {
        this.cellSize = cellSize;
    }

    private Point getCellKey(float x, float y) {
        return new Point((int)(x / cellSize), (int)(y / cellSize));
    }

    public void add(T obj, Rectangle bounds) {
        Point key = getCellKey(bounds.x, bounds.y);
        grid.computeIfAbsent(key, k -> new Array<>()).add(obj);
    }

    public Array<T> query(Rectangle bounds) {
        Array<T> result = new Array<>();

        int startX = (int)(bounds.x / cellSize);
        int endX = (int)((bounds.x + bounds.width) / cellSize);
        int startY = (int)(bounds.y / cellSize);
        int endY = (int)((bounds.y + bounds.height) / cellSize);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                Point key = new Point(x, y);
                Array<T> cell = grid.get(key);
                if (cell != null) result.addAll(cell);
            }
        }

        return result;
    }

    public void clear() {
        grid.clear();
    }*/
}
