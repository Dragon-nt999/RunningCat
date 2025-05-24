package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomMatrixPositions {
    public static List<List<Vector2>> getBlockPositions(int total) {
        List<Vector2> allPositions = new ArrayList<>();
        List<List<Vector2>> results = new ArrayList<>();
        Random random = new Random();

        for(int y = 0; y < GameGrid.WORLD_HEIGHT; y++) {
            for(int x = 0; x < (GameGrid.WORLD_WIDTH * 2); x++) {
                allPositions.add(new Vector2(x, y));
            }
        }

        Collections.shuffle(allPositions, random);

        for(int i = 0; i < Math.min(total, allPositions.size()); i++) {
            Vector2 start = allPositions.get(i);

            List<Vector2> collects = new ArrayList<>();
            collects.add(start);

            int j = 1;
            do {
                collects.add(new Vector2(start.x + j, start.y % 2));
            }while(j++ < 4);

            results.add(collects);
        }

        return results;
    }
}
