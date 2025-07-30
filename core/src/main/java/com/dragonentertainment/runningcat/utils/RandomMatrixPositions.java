package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.enums.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomMatrixPositions {
    private static int firstCount = Config.FIRST_NUM_BRICKS;

    private final static int row = 3;
    private static int col = Config.NUM_BIRCK_OF_ROW;
    public static List<Vector2> getBlockPositions(boolean isRespawn) {
        List<Vector2> results = new ArrayList<>();

        /*-----------------------------------------------
         * The First Bricks for game starts
         *-----------------------------------------------*/
        if(firstCount > 0 && !isRespawn) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < GameGrid.WORLD_WIDTH; j++) {
                    results.add(new Vector2(j, i));
                    firstCount--;
                }
            }
        }

        /*-----------------------------------------------
         * Shuffle GameGrid.allPositions
         *-----------------------------------------------*/
        Collections.shuffle(GameGrid.allPositions);


        /*-----------------------------------------------
         * Create the yPos list with the following conditions
         *-----------------------------------------------*/
        ArrayList<Vector2> yPos = new ArrayList<Vector2>();
        HashMap<Float, Float> lastXByY = new HashMap<>();


        /*-----------------------------------------------
         * Alway add this block of brick for begin
         *-----------------------------------------------*/
        yPos.add(new Vector2((int)GameGrid.WORLD_WIDTH, row));

        /*-----------------------------------------------
         * Set num of brick by level
         *-----------------------------------------------*/
        if(col > 2 && LevelManager.getInstance().isEffect()) {
            col--;
            LevelManager.getInstance().setEffect(false);
        }

        Gdx.app.log("LEVEL", col + "=====" + LevelManager.getInstance().getLevel() + LevelManager.getInstance().isEffect());

        for(Vector2 p : GameGrid.allPositions) {

            if(p.y > 0 && p.y % row == 0 && p.y < GameGrid.WORLD_HEIGHT
                    && yPos.size() < MathUtils.floor((float)GameGrid.WORLD_HEIGHT / row)) {

                /*-----------------------------------------------
                 * Prevent duplicate p
                 *-----------------------------------------------*/
                boolean allReadyExists = false;
                for (Vector2 existing : yPos) {
                    if(existing.epsilonEquals(p, 0.01f)) {
                        allReadyExists = true;
                        break;
                    }
                }
                if(allReadyExists) continue;

                /*-----------------------------------------------
                 * Ensure x > previous x + num when y values match
                 *-----------------------------------------------*/
                if(lastXByY.containsKey(p.y)) {
                    float lastX = lastXByY.get(p.y);
                    if(p.x <= lastX + col) continue;
                }

                /*-----------------------------------------------
                 * Add p when all conditions are met
                 *-----------------------------------------------*/
                yPos.add(p);
                lastXByY.put(p.y, p.x);
            }
        }

        /*-----------------------------------------------
         * Generate newP base on yPos
         * and add it to results
         *-----------------------------------------------*/
        for (Vector2 p : yPos) {
            int index = 0;

            do{
                Vector2 newP = new Vector2(p.x + index, p.y);
                results.add(newP);
                index++;
            }while (index <= col);

        }

        // Reset when restart game
        firstCount = Config.FIRST_NUM_BRICKS;

        //Gdx.app.log("BRICKS", results.toString());

        return results;
    }
}
