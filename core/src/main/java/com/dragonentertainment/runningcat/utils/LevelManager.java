package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.enums.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    public static int numOfBrick = 3;

    public static List<Vector2> getLevel(Vector2 start, Level level, int y) {
        List<Vector2> collects = new ArrayList<>();
        switch (level){
            case EASY:
                numOfBrick = 3;
                collects = levelEasy(start, y);
                break;
            case MEDIUM:
                numOfBrick = 2;
                collects = levelMedium(start, y);
                break;
            case HARD:
                numOfBrick = 1;
                collects = levelHard(start, y);
                break;
            default:
                break;
        }

        return collects;
    }

    private static List<Vector2> levelEasy(Vector2 start, int y) {
        List<Vector2> collects = new ArrayList<>();
        int x = 0;
        do {
            collects.add(new Vector2(start.x + x, y));
        }while(x++ < numOfBrick);

        return collects;
    }

    private static List<Vector2> levelMedium(Vector2 start, int y) {
        List<Vector2> collects = new ArrayList<>();
        int x = 0;
        do {
            collects.add(new Vector2(start.x + x, y));
        }while(x++ < numOfBrick);
        return collects;
    }

    private static List<Vector2> levelHard(Vector2 start, int y) {
        List<Vector2> collects = new ArrayList<>();
        int x = 0;
        do {
            collects.add(new Vector2(start.x + x, y + x));
        }while(x++ < numOfBrick);
        return collects;
    }
}
