package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.enums.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RandomMatrixPositions {
    private static int firstCount = Config.FIRST_NUM_BRICKS;
    public static List<List<Vector2>> getBlockPositions(boolean isRespawn, Level level) {
        List<List<Vector2>> results = new ArrayList<>();

        // First Render Bricks
        if(firstCount > 0 && !isRespawn) {
            List<Vector2> firstCollects = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < GameGrid.WORLD_WIDTH; j++) {
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

        /*-----------------------------------------------
         * Contain y for checking to avoid duplicate per loop
         *-----------------------------------------------*/
        ArrayList<Integer> prevY = new ArrayList<Integer>();


        for(int i = 0; i < Config.MAX_NUM_ROW; i++) {
            Vector2 start = GameGrid.allPositions.get(i);

            int y = 0;
            // random y
            do{
                y = MathUtils.random(2, GameGrid.WORLD_HEIGHT - 1);
            }while((y % 3 != 0) || (prevY.contains(y)));

            prevY.add(y);

            results.add(LevelManager.getLevel(start, level, y));
        }

        /*-----------------------------------------------
         * Sort position by maximum position x
         *-----------------------------------------------*/
        results.sort(Comparator.comparingDouble(group ->group.get(LevelManager.numOfBrick).x));

        // Reset when restart game
        firstCount = Config.FIRST_NUM_BRICKS;

        return results;
    }
}
