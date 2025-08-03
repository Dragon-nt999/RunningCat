package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;

public class HealthManager {
    private static int initialScore = 5;
    private static int scoreToIncreaseHelth = 100;
    private static final HealthManager instance = new HealthManager();

    public static HealthManager getInstance(){
        return instance;
    }

    public int getHealth() {
        return initialScore;
    }

    public void decreaseHealth() {
        initialScore--;
    }

    public void increaseHealth() {
        if(ScoreManager.getInstance().getScore() > 0
            && ScoreManager.getInstance().getScore() % scoreToIncreaseHelth == 0) {
            scoreToIncreaseHelth += 100;
            initialScore += (int)(ScoreManager.getInstance().getScore() / 100);
        }
    }

    public void reset() {
        initialScore = 5;
        scoreToIncreaseHelth = 100;

    }

}
