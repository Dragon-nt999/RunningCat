package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameData {
    private static GameData instance = new GameData();
    private static final String PREF_NAME = "runningcat_game";
    private static final String KEY_SCORE = "score";
    private static final String KEY_ATTEMPTS = "attempts";
    private int attempts = 0;
    private final Preferences prefs;

    public GameData() {
        prefs = Gdx.app.getPreferences(PREF_NAME);
    }

    public static GameData getInstance() {
        if(instance == null) {
            instance = new GameData();
        }

        return instance;
    }

    public void saveScore(int score) {
        prefs.putInteger(KEY_SCORE, score);
        prefs.flush();
    }

    public int getScore() {
        return  prefs.getInteger(KEY_SCORE, 0);
    }

    public void increaseAttempts() {
        attempts = prefs.getInteger(KEY_ATTEMPTS, 0);
        attempts++;

        prefs.putInteger(KEY_ATTEMPTS, attempts);
        prefs.flush();
    }

    public int getAttempts() {
        return prefs.getInteger(KEY_ATTEMPTS, 0);
    }

    public void clear(){
        prefs.clear();
        prefs.flush();
    }
}
