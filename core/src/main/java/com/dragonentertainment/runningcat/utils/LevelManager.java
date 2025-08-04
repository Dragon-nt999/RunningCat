package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.enums.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    private final static LevelManager instance = new LevelManager();
    private float level = 1f;
    private int numBrickPerLevel = Config.MAX_NUM_BRICK_ROW;

    public static LevelManager getInstance() {
        return instance;
    }

    public float getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public void increase() {
        if(ScoreManager.getInstance().getScore() > 0 && ScoreManager.getInstance().getScore() % 50 == 0 ) {
            this.level = (float) (ScoreManager.getInstance().getScore() / 50);
        }

        if((ScoreManager.getInstance().getScore() % 100 == 0)) {
            this.decreasedNumBrickPerLevel();
        }
    }
    public float parseLevelToSpeed() {
        return (float)(this.level / 100);
    }

    public void decreasedNumBrickPerLevel() {
        if(this.numBrickPerLevel > 2) {
            this.numBrickPerLevel--;
        }
    }

    public int getNumBrickPerLevel() {
        return this.numBrickPerLevel;
    }
    public void resetNumBrickPerLevel() {
        this.numBrickPerLevel = Config.MAX_NUM_BRICK_ROW;
    }
}
