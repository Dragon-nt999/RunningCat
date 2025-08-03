package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.enums.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    private final static LevelManager instance = new LevelManager();
    private float level = 1f;
    private boolean effect = false;

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
            this.effect = true;
        }
    }
    public float parseLevelToSpeed() {
        return (float)(this.level / 100);
    }

    public boolean isEffect() {
        return this.effect;
    }
    public void setEffect(boolean effect) {
        this.effect = effect;
    }
}
