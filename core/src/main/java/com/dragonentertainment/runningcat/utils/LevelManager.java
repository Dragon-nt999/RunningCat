package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.enums.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    private final static LevelManager instance = new LevelManager();
    private float level = 1f;

    public static LevelManager getInstance() {
        return instance;
    }

    public float getLevel() {
        return (float)(this.level / 100);
    }
    public void setLevel(float level) {
        this.level = level;
    }

    public void increase() {
        this.level = (float) ScoreManager.getInstance().getScore() / 50;
    }
    public int originLevel() {
        return (int)this.level;
    }
}
