package com.dragonentertainment.runningcat.utils;

public class ScoreManager {
    private static int GAME_SCORE_INITIAL = 10;
    private static final ScoreManager instance = new ScoreManager();
    private int score = 0;

    public static ScoreManager getInstance() {
        return instance;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore() {
        this.score += GAME_SCORE_INITIAL;
        if(this.score > GameData.getInstance().getScore()) {
            GameData.getInstance().saveScore(this.score);
        }
    }

    public void resetScore() {
        this.score = 0;
    }
}
