package com.dragonentertainment.runningcat.utils;

public class HealthManager {
    private static int INITIAL = 5;
    private static final HealthManager instance = new HealthManager();

    public static HealthManager getInstance(){
        return instance;
    }

    public int getHealth() {
        return INITIAL;
    }

    public void decreaseHealth() {
        INITIAL--;
    }

    public void increaseHealth() {
        INITIAL = ScoreManager.getInstance().getScore() % 100 == 0 ?
                    (int) ScoreManager.getInstance().getScore() / 100
                    : 0;
    }

}
