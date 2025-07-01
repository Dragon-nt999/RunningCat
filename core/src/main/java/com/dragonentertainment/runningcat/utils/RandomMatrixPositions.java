package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomMatrixPositions {
    private static int firstCount = 18;
    public static List<List<Vector2>> getBlockPositions(int total, boolean isRespawn) {
        List<List<Vector2>> results = new ArrayList<>();

        // First Render Bricks
        if(firstCount > 0 && !isRespawn) {
            List<Vector2> firstCollects = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 9; j++) {
                    firstCollects.add(new Vector2(j, i));
                    firstCount--;
                }
            }

            results.add(firstCollects);
        }

        /*-----------------------------------------------
         * Shuffle GameGrid.allPositions
         *-----------------------------------------------*/

        Collections.shuffle(GameGrid.allPositions);

        for(int i = 0; i < Math.min(total, GameGrid.allPositions.size()); i++) {
            List<Vector2> collects = new ArrayList<>();

            Vector2 start = GameGrid.allPositions.get(i);

            int x = 0;
            int y;
            // random y
            do{
                y = MathUtils.random(GameGrid.WORLD_HEIGHT - 1);
            }while(y % 4 != 0);

            // increase x
            do {
                collects.add(new Vector2(start.x + x, y));
            }while(x++ < 2);

            results.add(collects);
        }

        /*-----------------------------------------------
        * Code for Testing with full brick in floor
        *-----------------------------------------------*/
        /*for(int i = 0; i < 2; i++) {
            List<Vector2> collects = new ArrayList<>();
            for(int j = GameGrid.WORLD_WIDTH; j < GameGrid.WORLD_WIDTH * 2; j++) {
                collects.add(new Vector2(j, i));
            }
            results.add(collects);
        }
        for(int i = 4; i < 6; i++) {
            List<Vector2> collects = new ArrayList<>();

            collects.add(new Vector2(i - 2f, 5));

            results.add(collects);
        }*/

        // Reset when restart game
        firstCount = 18;

        return results;
    }
}
